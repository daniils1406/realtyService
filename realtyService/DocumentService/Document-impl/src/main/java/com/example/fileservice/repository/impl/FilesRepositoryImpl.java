package com.example.fileservice.repository.impl;

import com.example.fileservice.exception.FileDownloadingException;
import com.example.fileservice.mapper.jooq.FileJooqUnmapper;
import com.example.fileservice.model.jooq.schema.enums.EntityType;
import com.example.fileservice.model.jooq.schema.enums.FileStatus;
import com.example.fileservice.model.jooq.schema.enums.FileType;
import com.example.fileservice.model.jooq.schema.tables.pojos.FileEntity;
import com.example.fileservice.model.jooq.schema.tables.records.FileRecord;
import com.example.fileservice.repository.FilesRepository;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static com.example.fileservice.model.jooq.schema.tables.File.FILE_ENTITY;

@Repository
@RequiredArgsConstructor
public class FilesRepositoryImpl implements FilesRepository {

    private final DSLContext dslContext;

    private final FileJooqUnmapper fileJooqUnmapper;

    @Override
    public void save(FileEntity fileInfo) {
        dslContext.insertInto(FILE_ENTITY)
                .set(dslContext.newRecord(FILE_ENTITY, fileInfo))
                .execute();
    }


    @Override
    public void deleteViewByStorageName(String storageName) {
        System.out.println(storageName);
        dslContext.update(FILE_ENTITY)
                .set(FILE_ENTITY.FILE_STATUS, FileStatus.DELETE)
                .where(FILE_ENTITY.STORAGE_FILE_NAME.like("%" + storageName + "%"))
                .execute();
    }


    @Override
    public boolean existByIdAndType(UUID id, String fileType) {
        return dslContext.fetchExists(
                dslContext.selectOne()
                        .from(FILE_ENTITY)
                        .where(FILE_ENTITY.ID.eq(id))
                        .and(FILE_ENTITY.FILE_TYPE.eq(FileType.valueOf(fileType)))
        );
    }

    @Override
    public boolean existById(UUID id) {
        return dslContext.fetchExists(
                dslContext.selectOne()
                        .from(FILE_ENTITY)
                        .where(FILE_ENTITY.ID.eq(id))
        );
    }

    @Override
    public void updateByIdAndType(FileEntity file) {
        FileRecord record = fileJooqUnmapper.unmap(file);
        dslContext.update(FILE_ENTITY)
                .set(record)
                .where(FILE_ENTITY.ID.eq(file.getId()))
                .and(FILE_ENTITY.FILE_TYPE.eq(file.getFileType()))
                .execute();
    }

    @Override
    public int numberOfViewImagesOfRealtyById(UUID id) {
        return dslContext.fetchCount(
                dslContext.selectFrom(FILE_ENTITY)
                        .where(FILE_ENTITY.ID.eq(id))
                        .and(FILE_ENTITY.FILE_TYPE.eq(FileType.VIEW)));
    }

    @Override
    public FileEntity findViewByStorageName(String viewId) {
        return dslContext.selectFrom(FILE_ENTITY)
                .where(FILE_ENTITY.STORAGE_FILE_NAME.like("%" + viewId + "%"))
                .fetchOptional()
                .orElseThrow(() -> new FileDownloadingException("File not found"))
                .into(FileEntity.class);
    }

    @Override
    public EntityType findOutEntityOfId(UUID id) {
        return dslContext.select()
                .from(FILE_ENTITY)
                .where(FILE_ENTITY.ID.eq(id))
                .limit(1)
                .fetch()
                .getValue(0, FILE_ENTITY.ENTITY_TYPE);
    }

    @Override
    public List<FileType> findAllSaveDocumentsOfEntity(UUID id) {
        return dslContext.selectFrom(FILE_ENTITY)
                .where(FILE_ENTITY.ID.eq(id))
                .and(FILE_ENTITY.FILE_STATUS.eq(FileStatus.VERIFIED))
                .fetch()
                .getValues(FILE_ENTITY.FILE_TYPE);
    }

    @Override
    public void setSomeStatusOnFileByIdAndType(UUID id, String fileType, String status) {
        dslContext.update(FILE_ENTITY)
                .set(FILE_ENTITY.FILE_STATUS, FileStatus.valueOf(status))
                .where(FILE_ENTITY.ID.eq(id))
                .and(FILE_ENTITY.FILE_TYPE.eq(FileType.valueOf(fileType)))
                .execute();
    }

    @Override
    public List<String> findViewOfRealty(UUID realtyId) {
        return dslContext.selectFrom(FILE_ENTITY)
                .where(FILE_ENTITY.ID.eq(realtyId))
                .and(FILE_ENTITY.FILE_TYPE.eq(FileType.VIEW))
                .fetch(FILE_ENTITY.STORAGE_FILE_NAME);
    }

    @Override
    public void setNewEntityTypeById(UUID id, String newType) {
        dslContext.update(FILE_ENTITY)
                .set(FILE_ENTITY.ENTITY_TYPE, EntityType.valueOf(newType))
                .where(FILE_ENTITY.ID.eq(id))
                .execute();
    }


}
