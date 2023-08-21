package com.example.fileservice.repository;

import com.example.fileservice.model.jooq.schema.enums.EntityType;
import com.example.fileservice.model.jooq.schema.enums.FileStatus;
import com.example.fileservice.model.jooq.schema.enums.FileType;
import com.example.fileservice.model.jooq.schema.tables.pojos.FileEntity;

import java.util.List;
import java.util.UUID;

public interface FilesRepository {
    void save(FileEntity file);


    void deleteViewByStorageName(String storageName);


    boolean existByIdAndType(UUID id, String fileType);

    boolean existById(UUID id);

    void updateByIdAndType(FileEntity file);

    int numberOfViewImagesOfRealtyById(UUID id);

    FileEntity findViewByStorageName(String viewId);

    EntityType findOutEntityOfId(UUID id);

    List<FileType> findAllSaveDocumentsOfEntity(UUID id);

    void setSomeStatusOnFileByIdAndType(UUID id,String fileType,String status);

    List<String> findViewOfRealty(UUID realtyId);

    void setNewEntityTypeById(UUID id, String newType);
}
