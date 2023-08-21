package com.example.authenticationService.controller;

import com.example.authenticationService.exception.UnauthorizedException;
import com.example.authenticationService.rabbit.RabbitProducer;
import com.example.authenticationService.security.utils.AuthorizationHeaderUtil;
import com.example.authenticationService.security.utils.JwtUtil;
import api.HelpApi;
import dto.request.cianuser.CianUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/help")
public class HelpController implements HelpApi {

    private final RabbitProducer rabbitProducer;

    private final JwtUtil jwtUtil;

    private final AuthorizationHeaderUtil headerUtil;


    public void createTestMessageToResetPassword(@RequestBody CianUserRequest cianUserRequest) {
        rabbitProducer.sendMessageToResetPassword(cianUserRequest.getLogin());
    }

    @Override
    public void createTestMessageToResetLogin(HttpServletRequest request, String newLogin) {
        if (headerUtil.hasToken(request)) {
            String oldLogin = jwtUtil.parse(headerUtil.getToken(request)).getLogin();
            rabbitProducer.sendMessageToResetLogin(oldLogin, newLogin);
        } else {
            throw new UnauthorizedException();
        }
    }
}
