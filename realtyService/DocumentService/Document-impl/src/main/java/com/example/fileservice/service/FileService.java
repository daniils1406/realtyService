package com.example.fileservice.service;


import com.example.fileservice.model.jooq.schema.tables.pojos.FileEntity;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface FileService {

    void upload(UUID id, String entity, MultipartFile file, String fileType, boolean isDocument);

    void deleteView(String storageName);


    ResponseEntity<ByteArrayResource> downloadView(String storageName);

    boolean checkNecessaryFiles(UUID id);

    void setStatusOnSomeFile(UUID id,String fileType,String fileStatus);

    FileEntity findFileByStorageFileName(String name);

    List<String> getViewOfRealty(UUID realtyId);

    void setNewEntityTypeById(UUID id,String newType);
}
