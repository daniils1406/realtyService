package com.example.authenticationService.security.filters;

import com.example.authenticationService.model.jooq.schema.enums.Usertype;
import com.example.authenticationService.model.jooq.schema.tables.pojos.CianUserEntity;
import com.example.authenticationService.model.jooq.schema.tables.pojos.RefreshTokenEntity;
import com.example.authenticationService.redis.RedisUserService;
import com.example.authenticationService.repository.CianUserRepository;
import com.example.authenticationService.repository.RefreshTokenRepository;
import com.example.authenticationService.security.authentication.RefreshTokenAuthentication;
import com.example.authenticationService.security.details.UserDetailsImpl;
import com.example.authenticationService.security.utils.AuthorizationHeaderUtil;
import com.example.authenticationService.security.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;

import static java.time.temporal.ChronoUnit.MILLIS;

@Component
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static final String USERNAME_PARAMETER = "login";
    public static final String AUTHENTICATION_URL = "/**/logIn";

    private final ObjectMapper objectMapper;

    private final JwtUtil jwtUtil;

    private final AuthorizationHeaderUtil authorizationHeaderUtil;

    private final RefreshTokenRepository refreshTokenRepository;

    private final CianUserRepository cianUserRepository;

    private final RedisUserService redisUserService;


    @Value("${jwt.refresh_token_expires_time}")
    private long REFRESH_TOKEN_EXPIRES_TIME;

    public JwtAuthenticationFilter(AuthenticationConfiguration authenticationConfiguration,
                                   ObjectMapper objectMapper,
                                   JwtUtil jwtUtil,
                                   AuthorizationHeaderUtil authorizationHeaderUtil,
                                   RefreshTokenRepository refreshTokenRepository,
                                   CianUserRepository cianUserRepository,
                                   RedisUserService redisUserService) throws Exception {
        super(authenticationConfiguration.getAuthenticationManager());
        this.setUsernameParameter(USERNAME_PARAMETER);
        this.setFilterProcessesUrl(AUTHENTICATION_URL);
        this.objectMapper = objectMapper;
        this.jwtUtil = jwtUtil;
        this.authorizationHeaderUtil = authorizationHeaderUtil;
        this.refreshTokenRepository = refreshTokenRepository;
        this.cianUserRepository = cianUserRepository;
        this.redisUserService = redisUserService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (authorizationHeaderUtil.hasToken(request)) {
            String refreshToken = authorizationHeaderUtil.getToken(request);
            String oldAccessToken = authorizationHeaderUtil.getOldToken(request);
            CianUserEntity user = cianUserRepository.findById(
                    refreshTokenRepository.findById(UUID.fromString(refreshToken)).getUserId(),
                    Usertype.CLIENT);
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String[] parts = oldAccessToken.split("\\.");
            String decodeCode = new String(decoder.decode(parts[1]));
            JSONObject oldToken = null;
            try {
                Object o = new JSONParser().parse(decodeCode);
                oldToken = (JSONObject) o;
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            String login = (String) oldToken.get("sub");
            String role = (String) oldToken.get("role");
            if (login.equals(user.getLogin()) && role.equals(user.getRole().toString())) {
                RefreshTokenAuthentication authentication = new RefreshTokenAuthentication(refreshToken);
                return super.getAuthenticationManager().authenticate(authentication);
            } else {
                try {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    return null;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            return super.attemptAuthentication(request, response);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        response.setContentType("application/json");
        GrantedAuthority currentAuthority = authResult.getAuthorities().stream().findFirst().orElseThrow();
        String login = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();
        String issuer = request.getRequestURL().toString();

        UUID userId = cianUserRepository.findByLogin(login).getId();

        Map<String, String> tokens = jwtUtil.generateTokens(userId.toString(), login, currentAuthority.getAuthority(), issuer);


        if (authorizationHeaderUtil.hasToken(request)) {
            if (Math.abs(MILLIS.between(LocalDateTime.now(), refreshTokenRepository.findByUserId(userId).getInsertDate())) > REFRESH_TOKEN_EXPIRES_TIME) {
                refreshTokenRepository.deleteByUserId(userId);
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            } else {
                refreshTokenRepository.updateRefreshTokenOfUser(userId, UUID.fromString(tokens.get("refreshToken")));
            }
        } else {
            if (refreshTokenRepository.userIdExists(userId)) {
                refreshTokenRepository.updateRefreshTokenOfUser(userId, UUID.fromString(tokens.get("refreshToken")));
            } else {
                refreshTokenRepository.save(new RefreshTokenEntity(UUID.fromString(tokens.get("refreshToken")), userId, LocalDateTime.now()));
            }
        }


        redisUserService.addTokenToUser(cianUserRepository.findByLogin(login), tokens.get("accessToken"));


        objectMapper.writeValue(response.getOutputStream(), tokens);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Wrong password or email");
    }
}
