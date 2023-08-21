package com.example.fileservice.mapper.jooq;

import com.example.fileservice.model.jooq.schema.enums.FileStatus;
import com.example.fileservice.model.jooq.schema.tables.pojos.FileEntity;
import com.example.fileservice.model.jooq.schema.tables.records.FileRecord;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jooq.DSLContext;
import org.jooq.RecordUnmapper;
import org.jooq.exception.MappingException;
import org.springframework.stereotype.Component;

import static com.example.fileservice.model.jooq.schema.tables.File.FILE_ENTITY;

@Component
@RequiredArgsConstructor
public class FileJooqUnmapper implements RecordUnmapper<FileEntity, FileRecord> {

    private final DSLContext dslContext;

    @Override
    public @NotNull FileRecord unmap(FileEntity source) throws MappingException {
        FileRecord record = dslContext.newRecord(FILE_ENTITY);
        record.setId(source.getId());
        record.setOriginalFileName(source.getOriginalFileName());
        record.setStorageFileName(source.getStorageFileName());
        record.setSize(source.getSize());
        record.setMimeType(source.getMimeType());
        record.setUpdateDate(source.getUpdateDate());
        record.setPath(source.getPath());
        record.setFileType(source.getFileType());
        record.setEntityType(source.getEntityType());
        record.setFileStatus(FileStatus.INSERT);
        return record;
    }

}
