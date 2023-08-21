package com.example.advertismentService.grpc;

import advertisement.*;
import com.example.advertismentService.model.jooq.schema.enums.FlatOrHouse;
import com.google.protobuf.Any;
import com.google.rpc.ErrorInfo;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import com.example.advertismentService.exception.EntityNotFoundException;
import com.example.advertismentService.repository.AgencyRepository;
import com.example.advertismentService.repository.BuilderRepository;
import com.example.advertismentService.repository.RealtyRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@GrpcService
@Component
@RequiredArgsConstructor
public class GrpcAdvertisementServiceImpl extends AdvertisementServiceGrpc.AdvertisementServiceImplBase {

    private final RealtyRepository realtyRepository;

    private final AgencyRepository agencyRepository;

    private final BuilderRepository builderRepository;

    @Override
    public void bannedAllRealtyOfUser(IdRequest request, StreamObserver<Empty> responseObserver) {
        if (realtyRepository.checkExists(UUID.fromString(request.getId()))) {
            realtyRepository.bannedAllRealtyOfSomeOwner(UUID.fromString(request.getId()));
            Empty empty = Empty.newBuilder().build();
            responseObserver.onNext(empty);
            responseObserver.onCompleted();
        } else {
            com.google.rpc.Status status = com.google.rpc.Status.newBuilder()
                    .setCode(com.google.rpc.Code.NOT_FOUND.getNumber())
                    .setMessage("This user have not any realty")
                    .addDetails(Any.pack(ErrorInfo.newBuilder()
                            .setReason("RealtyNotFound")
                            .putMetadata("id", request.getId())
                            .build()))
                    .build();
            responseObserver.onError(StatusProto.toStatusRuntimeException(status));
        }
    }

    @Override
    public void verifySomeRealtyOfUser(IdRequest request, StreamObserver<Empty> responseObserver) {
        if (realtyRepository.checkExists(UUID.fromString(request.getId()))) {
            realtyRepository.setStatus(UUID.fromString(request.getId()), "OPEN");
            Empty empty = Empty.newBuilder().build();
            responseObserver.onNext(empty);
            responseObserver.onCompleted();
        } else {
            com.google.rpc.Status status = com.google.rpc.Status.newBuilder()
                    .setCode(com.google.rpc.Code.NOT_FOUND.getNumber())
                    .setMessage("This user have not any realty")
                    .addDetails(Any.pack(ErrorInfo.newBuilder()
                            .setReason("RealtyNotFound")
                            .putMetadata("id", request.getId())
                            .build()))
                    .build();
            responseObserver.onError(StatusProto.toStatusRuntimeException(status));
        }
    }

    @Override
    public void deleteAllRealtyOfUser(IdRequest request, StreamObserver<Empty> responseObserver) {
        if (realtyRepository.checkExists(UUID.fromString(request.getId()))) {
            realtyRepository.deleteAllRealtyOfSomeOwner(UUID.fromString(request.getId()));
            Empty empty = Empty.newBuilder().build();
            responseObserver.onNext(empty);
            responseObserver.onCompleted();
        } else {
            com.google.rpc.Status status = com.google.rpc.Status.newBuilder()
                    .setCode(com.google.rpc.Code.NOT_FOUND.getNumber())
                    .setMessage("This user have not any realty")
                    .addDetails(Any.pack(ErrorInfo.newBuilder()
                            .setReason("RealtyNotFound")
                            .putMetadata("id", request.getId())
                            .build()))
                    .build();
            responseObserver.onError(StatusProto.toStatusRuntimeException(status));
        }
    }

    @Override
    public void verifyAllRealtyOfUser(IdRequest request, StreamObserver<Empty> responseObserver) {
        if (realtyRepository.checkExists(UUID.fromString(request.getId()))) {
            realtyRepository.verifyAllRealtyOfSomeOwner(UUID.fromString(request.getId()));
            Empty empty = Empty.newBuilder().build();
            responseObserver.onNext(empty);
            responseObserver.onCompleted();
        } else {
            com.google.rpc.Status status = com.google.rpc.Status.newBuilder()
                    .setCode(com.google.rpc.Code.NOT_FOUND.getNumber())
                    .setMessage("This user have not any realty")
                    .addDetails(Any.pack(ErrorInfo.newBuilder()
                            .setReason("RealtyNotFound")
                            .putMetadata("id", request.getId())
                            .build()))
                    .build();
            responseObserver.onError(StatusProto.toStatusRuntimeException(status));
        }
    }


    @Override
    public void findOwnerOfRealty(IdRequest request, StreamObserver<OwnerId> responseObserver) {
        try {
            FlatOrHouse flatOrHouse = realtyRepository.findOutTypeOfRealty(UUID.fromString(request.getId()));
            if (FlatOrHouse.FLAT.equals(flatOrHouse)) {
                UUID id = realtyRepository.findById(UUID.fromString(request.getId()), flatOrHouse).getOwnerId();
                OwnerId ownerId = OwnerId.newBuilder()
                        .setId(id.toString())
                        .build();
                responseObserver.onNext(ownerId);
                responseObserver.onCompleted();
            }
        } catch (EntityNotFoundException ex) {
            com.google.rpc.Status status = com.google.rpc.Status.newBuilder()
                    .setCode(com.google.rpc.Code.NOT_FOUND.getNumber())
                    .setMessage("Realty with id " + request.getId() + " not found!")
                    .addDetails(Any.pack(ErrorInfo.newBuilder()
                            .setReason("RealtyNotFound")
                            .putMetadata("id", request.getId())
                            .build()))
                    .build();
            responseObserver.onError(StatusProto.toStatusRuntimeException(status));
        }
    }

    @Override
    public void checkOrganisationExist(OrganisationRequest request, StreamObserver<OrganisationExist> responseObserver) {
        boolean isExist = false;
        switch (request.getOrganisation()) {
            case AGENCY -> {
                isExist = agencyRepository.checkExists(UUID.fromString(request.getId()));
            }
            case BUILDER -> {
                isExist = builderRepository.checkExists(UUID.fromString(request.getId()));
            }
        }
        OrganisationExist organisationExist = OrganisationExist.newBuilder()
                .setExist(isExist)
                .build();
        responseObserver.onNext(organisationExist);
        responseObserver.onCompleted();
    }
}
