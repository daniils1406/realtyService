package com.example.fileservice.controller;

import api.FileApi;
import com.example.fileservice.exception.NotYourDocumentException;
import com.example.fileservice.exception.NotYourRealtyException;
import com.example.fileservice.grpc.GrpcAdvertisementServiceImpl;

import com.example.fileservice.model.jooq.schema.enums.FileType;
import com.example.fileservice.model.jooq.schema.tables.pojos.FileEntity;
import com.example.fileservice.security.utils.AuthorizationHeaderUtil;
import com.example.fileservice.security.utils.JwtUtil;
import com.example.fileservice.service.FileService;
import dto.UpdateStatusRequest;
import dto.request.CheckRequest;
import dto.request.FileNameRequest;
import dto.response.CheckResponse;
import dto.response.FileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
public class FileController implements FileApi {
    private final FileService fileService;
    private final JwtUtil jwtUtil;
    private final AuthorizationHeaderUtil authorizationHeaderUtil;
    private final GrpcAdvertisementServiceImpl grpcAdvertisementService;

    private UUID getUUUIDFromToken(HttpServletRequest request) {
        return UUID.fromString(jwtUtil.parse(authorizationHeaderUtil.getToken(request)).getId());
    }

    @Override
    public FileResponse uploadImage(HttpServletRequest request, UUID id, String entity, MultipartFile[] file, String[] fileType) {
        UUID userId = getUUUIDFromToken(request);
        if (userId.equals(grpcAdvertisementService.findOutOwnerId(id))) {
            for (int i = 0; i < file.length; i++) {
                fileService.upload(id, entity, file[i], fileType[i], false);
            }
            return FileResponse.builder()
                    .message("Images were successfully uploaded")
                    .build();
        } else {
            throw new NotYourRealtyException();
        }
    }

    @Override
    public FileResponse uploadDocument(HttpServletRequest request, UUID id, String entity, MultipartFile[] file, String[] fileType) {
        for (int i = 0; i < file.length; i++) {
            if (fileType[i].equals(FileType.EGRN.toString())) {
                UUID userId = getUUUIDFromToken(request);
                if (userId.equals(grpcAdvertisementService.findOutOwnerId(id))) {
                    fileService.upload(id, entity, file[i], fileType[i], true);
                } else {
                    throw new NotYourRealtyException();
                }
            } else {
                fileService.upload(id, entity, file[i], fileType[i], true);
            }

        }
        return FileResponse.builder()
                .message("Documents were successfully uploaded")
                .build();
    }


    @Override
    public FileResponse deleteView(HttpServletRequest request, FileNameRequest fileRequest) {
        UUID userId = getUUUIDFromToken(request);
        FileEntity file = fileService.findFileByStorageFileName(fileRequest.getFileName());
        if (userId.equals(file.getId())) {
            fileService.deleteView(fileRequest.getFileName());
            return FileResponse.builder()
                    .message("Files were successfully deleted")
                    .build();
        } else {
            throw new NotYourRealtyException();
        }
    }


    @Override
    public ResponseEntity<ByteArrayResource> downloadView(FileNameRequest fileNameRequest) {
        return fileService.downloadView(fileNameRequest.getFileName());
    }

    @Override
    public CheckResponse checkForTheNecessaryFiles(CheckRequest idOfEntity) {
        return CheckResponse.builder()
                .isVerify(fileService.checkNecessaryFiles(idOfEntity.getId()))
                .build();
    }

    @Override
    public void setNewStatusOfSomeDocument(UpdateStatusRequest updateStatusRequest) {
        fileService.setStatusOnSomeFile(updateStatusRequest.getId(), updateStatusRequest.getFileType(), updateStatusRequest.getFileStatus());
    }

    @Override
    public List<String> getViewOfRealty(UUID realtyId) {
        return fileService.getViewOfRealty(realtyId);
    }
}
