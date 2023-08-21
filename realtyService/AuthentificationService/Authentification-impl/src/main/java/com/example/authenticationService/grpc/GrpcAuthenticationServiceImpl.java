package com.example.authenticationService.grpc;

import com.example.authenticationService.exception.EntityNotFoundException;
import com.example.authenticationService.model.jooq.schema.enums.Status;
import com.example.authenticationService.model.jooq.schema.enums.Usertype;
import com.example.authenticationService.service.CianUserService;
import authentication.*;
import com.google.protobuf.Any;
import com.google.rpc.ErrorInfo;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@GrpcService
@Component
@RequiredArgsConstructor
public class GrpcAuthenticationServiceImpl extends CianUserServiceGrpc.CianUserServiceImplBase {


    private final CianUserService cianUserService;


    @Override
    public void findById(IdRequest idRequest, StreamObserver<CianUserResponse> responseObserver) {
        dto.response.cianuser.CianUserResponse cianUser = cianUserService.getUserById(UUID.fromString(idRequest.getId()));

        CianUserResponse cianUserResponse = CianUserResponse.newBuilder()
                .setId(cianUser.getId().toString())
                .setFirstName(cianUser.getFirstName())
                .setLastName(cianUser.getLastName())
                .setPatronymic(cianUser.getPatronymic())
                .setPhone(cianUser.getPhone())
                .setLogin(cianUser.getLogin())
                .setRole(Role.valueOf(cianUserService.getUserRole(cianUser.getId(), cianUserService.findOutUsertypeOfId(cianUser.getId())).toString()))
                .build();
        responseObserver.onNext(cianUserResponse);
        responseObserver.onCompleted();
    }


    @Override
    public void bannedById(IdRequest request, StreamObserver<Empty> responseObserver) {
        cianUserService.bannedById(UUID.fromString(request.getId()));
        Empty empty = Empty.newBuilder().build();
        responseObserver.onNext(empty);
        responseObserver.onCompleted();
    }


    @Override
    public void verifiedById(IdRequest request, StreamObserver<Empty> responseObserver) {
        Usertype usertype = cianUserService.findOutUsertypeOfId(UUID.fromString(request.getId()));
        if (cianUserService.getUserStatus(UUID.fromString(request.getId()), usertype).equals(Status.DELETE)) {
            com.google.rpc.Status status = com.google.rpc.Status.newBuilder()
                    .setCode(com.google.rpc.Code.NOT_FOUND.getNumber())
                    .setMessage("Person with id " + request.getId() + " was deleted!")
                    .addDetails(Any.pack(ErrorInfo.newBuilder()
                            .setReason("PersonDeleted")
                            .putMetadata("id", request.getId())
                            .build()))
                    .build();
            responseObserver.onError(StatusProto.toStatusRuntimeException(status));
        } else {
            cianUserService.verifySomeUser(UUID.fromString(request.getId()));
            Empty empty = Empty.newBuilder().build();
            responseObserver.onNext(empty);
            responseObserver.onCompleted();
        }
    }


    @Override
    public void findCompanyWhichPersonBelong(IdRequest request, StreamObserver<CompanyIdPersonBelongsTo> responseObserver) {
        try {
            UUID organisationId = cianUserService.getCompanyOfRepresentative(UUID.fromString(request.getId()));
            CompanyIdPersonBelongsTo companyIdPersonBelongsTo = CompanyIdPersonBelongsTo.newBuilder()
                    .setId(organisationId.toString())
                    .build();
            responseObserver.onNext(companyIdPersonBelongsTo);
            responseObserver.onCompleted();
        } catch (EntityNotFoundException ex) {
            com.google.rpc.Status status = com.google.rpc.Status.newBuilder()
                    .setCode(com.google.rpc.Code.NOT_FOUND.getNumber())
                    .setMessage("Person with id " + request.getId() + " not found!")
                    .addDetails(Any.pack(ErrorInfo.newBuilder()
                            .setReason("PersonNotFound")
                            .putMetadata("id", request.getId())
                            .build()))
                    .build();
            responseObserver.onError(StatusProto.toStatusRuntimeException(status));
        }
    }

    @Override
    public void changeClientToOwner(IdRequest request, StreamObserver<Empty> responseObserver) {
        try {
            cianUserService.getUserById(UUID.fromString(request.getId()));
            cianUserService.updateClientToOwner(UUID.fromString(request.getId()));
            Empty empty = Empty.newBuilder().build();
            responseObserver.onNext(empty);
            responseObserver.onCompleted();
        } catch (EntityNotFoundException ex) {
            com.google.rpc.Status status = com.google.rpc.Status.newBuilder()
                    .setCode(com.google.rpc.Code.NOT_FOUND.getNumber())
                    .setMessage("Person with id " + request.getId() + " not found!")
                    .addDetails(Any.pack(ErrorInfo.newBuilder()
                            .setReason("PersonNotFound")
                            .putMetadata("id", request.getId())
                            .build()))
                    .build();
            responseObserver.onError(StatusProto.toStatusRuntimeException(status));
        }
    }


}
