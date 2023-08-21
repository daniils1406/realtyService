package com.example.authenticationService.controller;

import com.example.authenticationService.exception.ChangeForeignAccount;
import com.example.authenticationService.security.entity.ParsedToken;
import com.example.authenticationService.security.utils.AuthorizationHeaderUtil;
import com.example.authenticationService.security.utils.JwtUtil;
import com.example.authenticationService.service.CianUserService;
import api.CianUserApi;
import dto.EntityPage;
import dto.request.IdRequest;
import dto.request.LoginRequest;
import dto.request.PasswordRequest;
import dto.request.cianuser.CianUserRequest;
import dto.request.cianuser.CianUserUpdateRequest;
import dto.response.cianuser.CianUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
public class CianUserController implements CianUserApi {

    private final CianUserService cianUserService;

    private final JwtUtil jwtUtil;

    private final AuthorizationHeaderUtil authorizationHeaderUtil;

    private ParsedToken getParsedToken(HttpServletRequest request) {
        return jwtUtil.parse(authorizationHeaderUtil.getToken(request));
    }


    @Override
    public EntityPage getAllUsers(int page, Map<String, String> columnsAndOrder, List<String> role, List<String> status) {
        if (columnsAndOrder == null) {
            columnsAndOrder = new HashMap<>();
            columnsAndOrder.put("id", "asc");
        }

        if (role.isEmpty()) {
            role = List.of("CLIENT", "OWNER", "ADMIN", "AGENT", "BUILDER", "AGENCY");
        }
        if (status.isEmpty()) {
            status = List.of("REGISTERED", "VERIFIED", "DELETE", "BANNED");
        }
        return cianUserService.findAll(page, columnsAndOrder, role, status);
    }

    @Override
    public CianUserResponse createNewCianUser(CianUserRequest cianUserRequest) {
        return cianUserService.createNewCianUser(cianUserRequest);
    }

    @Override
    public CianUserResponse updateCianUserById(HttpServletRequest request, CianUserUpdateRequest cianUserUpdateRequest) {
        ParsedToken parsedToken = getParsedToken(request);
        if (parsedToken.getId().equals(cianUserUpdateRequest.getId().toString())) {
            return cianUserService.updateCianUser(cianUserUpdateRequest);
        } else {
            throw new ChangeForeignAccount();
        }
    }

    @Override
    public void deleteCianUserById(HttpServletRequest request, IdRequest idRequest) {
        ParsedToken parsedToken = getParsedToken(request);
        if (parsedToken.getId().equals(idRequest.getId().toString())) {
            cianUserService.deleteById(idRequest.getId());
        } else {
            throw new ChangeForeignAccount();
        }
    }

    @Override
    public CianUserResponse getCianUserById(IdRequest idRequest) {
        return cianUserService.getUserById(idRequest.getId());
    }

    @Override
    public void approveCianUserById(String token) {
        cianUserService.approveById(token);
    }

    @Override
    public void bannedCianUserById(IdRequest idRequest) {
        cianUserService.bannedById(idRequest.getId());
    }

    @Override
    public void resetPasswordUser(PasswordRequest passwordAndToken) {
        cianUserService.resetPassword(passwordAndToken);
    }

    @Override
    public void resetLoginUser(LoginRequest loginAndToken) {
        cianUserService.resetLogin(loginAndToken);
    }


}
