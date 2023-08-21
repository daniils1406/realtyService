package com.example.fileservice.mapper;

import com.example.fileservice.model.jooq.schema.tables.pojos.FileEntity;
import dto.FileInfo;
import dto.response.FileResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileInfoMapper {
    FileEntity fromInfoToEntity(FileInfo fileInfo);

    FileResponse fromEntityToResponse(FileEntity file);
}
