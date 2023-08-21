package com.example.advertismentService.controllers;

import api.BuilderApi;
import com.example.advertismentService.exception.builder.NotYourBuilderException;
import com.example.advertismentService.grpc.GrpcAuthenticationServiceImpl;
import com.example.advertismentService.security.entity.ParsedToken;
import com.example.advertismentService.security.utils.AuthorizationHeaderUtil;
import com.example.advertismentService.security.utils.JwtUtil;
import dto.EntityPage;

import dto.request.IdRequest;
import dto.request.builder.BuilderRequest;
import dto.request.builder.BuilderUpdateRequest;
import dto.response.builder.BuilderAdminWithComplexesResponse;
import dto.response.builder.BuilderResponse;
import dto.response.builder.BuilderWithComplexesResponse;
import lombok.RequiredArgsConstructor;
import com.example.advertismentService.service.BuilderService;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class BuilderController implements BuilderApi {

    private final BuilderService builderService;

    private final JwtUtil jwtUtil;

    private final AuthorizationHeaderUtil authorizationHeaderUtil;

    private final GrpcAuthenticationServiceImpl grpcAuthenticationService;

    private ParsedToken getParsedToken(HttpServletRequest request) {
        return jwtUtil.parse(authorizationHeaderUtil.getToken(request));
    }

    @Override
    public EntityPage getAllBuilders(int page, Map<String, String> columnsAndOrder) {
        if (columnsAndOrder == null) {
            columnsAndOrder = new HashMap<>();
            columnsAndOrder.put("id", "asc");
        }
        return builderService.findAll(page, List.of("VERIFIED"), columnsAndOrder, false);
    }

    @Override
    public BuilderResponse createNewBuilder(BuilderRequest builderData) {
        return builderService.createBuilder(builderData);
    }

    @Override
    public BuilderResponse updateBuilderById(HttpServletRequest request, BuilderUpdateRequest newBuilder) {
        ParsedToken parsedToken = getParsedToken(request);
        if (newBuilder.getId().equals(grpcAuthenticationService.findCompanyWhichPersonBelong(UUID.fromString(parsedToken.getId())))) {
            return builderService.updateBuilderById(newBuilder);
        } else {
            throw new NotYourBuilderException();
        }
    }

    @Override
    public BuilderWithComplexesResponse getBuilderById(IdRequest builderId) {
        return builderService.findById(builderId.getId());
    }

    @Override
    public void deleteBuilderById(HttpServletRequest request, IdRequest builderId) {
        ParsedToken parsedToken = getParsedToken(request);
        if (builderId.getId().equals(grpcAuthenticationService.findCompanyWhichPersonBelong(UUID.fromString(parsedToken.getId())))) {
            builderService.deleteById(builderId.getId());
        } else {
            throw new NotYourBuilderException();
        }
    }

    @Override
    public void approveBuilderById(IdRequest builderId) {
        builderService.approveById(builderId.getId());
    }

    @Override
    public EntityPage getAllBuildersByAdmin(int page, List<String> status, Map<String, String> columnsAndOrder) {
        if (columnsAndOrder == null) {
            columnsAndOrder = new HashMap<>();
            columnsAndOrder.put("id", "asc");
        }
        return builderService.findAll(page, status, columnsAndOrder, true);
    }

    @Override
    public BuilderAdminWithComplexesResponse getBuilderByIdByAdmin(IdRequest request) {
        return builderService.findByIdByAdmin(request.getId());
    }


}
