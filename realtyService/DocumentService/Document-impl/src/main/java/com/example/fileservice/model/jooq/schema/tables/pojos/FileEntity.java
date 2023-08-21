/*
 * This file is generated by jOOQ.
 */
package com.example.fileservice.model.jooq.schema.tables.pojos;


import com.example.fileservice.entity.Entity;
import com.example.fileservice.model.jooq.schema.enums.EntityType;
import com.example.fileservice.model.jooq.schema.enums.FileStatus;
import com.example.fileservice.model.jooq.schema.enums.FileType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class FileEntity extends Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;
    private String originalFileName;
    private String storageFileName;
    private Integer size;
    private String mimeType;
    private LocalDate insertDate;
    private LocalDate updateDate;
    private String path;
    private FileType fileType;
    private FileStatus fileStatus;
    private EntityType entityType;

    public FileEntity() {}

    public FileEntity(FileEntity value) {
        this.id = value.id;
        this.originalFileName = value.originalFileName;
        this.storageFileName = value.storageFileName;
        this.size = value.size;
        this.mimeType = value.mimeType;
        this.insertDate = value.insertDate;
        this.updateDate = value.updateDate;
        this.path = value.path;
        this.fileType = value.fileType;
        this.fileStatus = value.fileStatus;
        this.entityType = value.entityType;
    }

    public FileEntity(
        UUID id,
        String originalFileName,
        String storageFileName,
        Integer size,
        String mimeType,
        LocalDate insertDate,
        LocalDate updateDate,
        String path,
        FileType fileType,
        FileStatus fileStatus,
        EntityType entityType
    ) {
        this.id = id;
        this.originalFileName = originalFileName;
        this.storageFileName = storageFileName;
        this.size = size;
        this.mimeType = mimeType;
        this.insertDate = insertDate;
        this.updateDate = updateDate;
        this.path = path;
        this.fileType = fileType;
        this.fileStatus = fileStatus;
        this.entityType = entityType;
    }

    /**
     * Getter for <code>public.file.id</code>.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for <code>public.file.id</code>.
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Getter for <code>public.file.original_file_name</code>.
     */
    public String getOriginalFileName() {
        return this.originalFileName;
    }

    /**
     * Setter for <code>public.file.original_file_name</code>.
     */
    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    /**
     * Getter for <code>public.file.storage_file_name</code>.
     */
    public String getStorageFileName() {
        return this.storageFileName;
    }

    /**
     * Setter for <code>public.file.storage_file_name</code>.
     */
    public void setStorageFileName(String storageFileName) {
        this.storageFileName = storageFileName;
    }

    /**
     * Getter for <code>public.file.size</code>.
     */
    public Integer getSize() {
        return this.size;
    }

    /**
     * Setter for <code>public.file.size</code>.
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * Getter for <code>public.file.mime_type</code>.
     */
    public String getMimeType() {
        return this.mimeType;
    }

    /**
     * Setter for <code>public.file.mime_type</code>.
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    /**
     * Getter for <code>public.file.insert_date</code>.
     */
    public LocalDate getInsertDate() {
        return this.insertDate;
    }

    /**
     * Setter for <code>public.file.insert_date</code>.
     */
    public void setInsertDate(LocalDate insertDate) {
        this.insertDate = insertDate;
    }

    /**
     * Getter for <code>public.file.update_date</code>.
     */
    public LocalDate getUpdateDate() {
        return this.updateDate;
    }

    /**
     * Setter for <code>public.file.update_date</code>.
     */
    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * Getter for <code>public.file.path</code>.
     */
    public String getPath() {
        return this.path;
    }

    /**
     * Setter for <code>public.file.path</code>.
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Getter for <code>public.file.file_type</code>.
     */
    public FileType getFileType() {
        return this.fileType;
    }

    /**
     * Setter for <code>public.file.file_type</code>.
     */
    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    /**
     * Getter for <code>public.file.file_status</code>.
     */
    public FileStatus getFileStatus() {
        return this.fileStatus;
    }

    /**
     * Setter for <code>public.file.file_status</code>.
     */
    public void setFileStatus(FileStatus fileStatus) {
        this.fileStatus = fileStatus;
    }

    /**
     * Getter for <code>public.file.entity_type</code>.
     */
    public EntityType getEntityType() {
        return this.entityType;
    }

    /**
     * Setter for <code>public.file.entity_type</code>.
     */
    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final FileEntity other = (FileEntity) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        if (this.originalFileName == null) {
            if (other.originalFileName != null)
                return false;
        }
        else if (!this.originalFileName.equals(other.originalFileName))
            return false;
        if (this.storageFileName == null) {
            if (other.storageFileName != null)
                return false;
        }
        else if (!this.storageFileName.equals(other.storageFileName))
            return false;
        if (this.size == null) {
            if (other.size != null)
                return false;
        }
        else if (!this.size.equals(other.size))
            return false;
        if (this.mimeType == null) {
            if (other.mimeType != null)
                return false;
        }
        else if (!this.mimeType.equals(other.mimeType))
            return false;
        if (this.insertDate == null) {
            if (other.insertDate != null)
                return false;
        }
        else if (!this.insertDate.equals(other.insertDate))
            return false;
        if (this.updateDate == null) {
            if (other.updateDate != null)
                return false;
        }
        else if (!this.updateDate.equals(other.updateDate))
            return false;
        if (this.path == null) {
            if (other.path != null)
                return false;
        }
        else if (!this.path.equals(other.path))
            return false;
        if (this.fileType == null) {
            if (other.fileType != null)
                return false;
        }
        else if (!this.fileType.equals(other.fileType))
            return false;
        if (this.fileStatus == null) {
            if (other.fileStatus != null)
                return false;
        }
        else if (!this.fileStatus.equals(other.fileStatus))
            return false;
        if (this.entityType == null) {
            if (other.entityType != null)
                return false;
        }
        else if (!this.entityType.equals(other.entityType))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.originalFileName == null) ? 0 : this.originalFileName.hashCode());
        result = prime * result + ((this.storageFileName == null) ? 0 : this.storageFileName.hashCode());
        result = prime * result + ((this.size == null) ? 0 : this.size.hashCode());
        result = prime * result + ((this.mimeType == null) ? 0 : this.mimeType.hashCode());
        result = prime * result + ((this.insertDate == null) ? 0 : this.insertDate.hashCode());
        result = prime * result + ((this.updateDate == null) ? 0 : this.updateDate.hashCode());
        result = prime * result + ((this.path == null) ? 0 : this.path.hashCode());
        result = prime * result + ((this.fileType == null) ? 0 : this.fileType.hashCode());
        result = prime * result + ((this.fileStatus == null) ? 0 : this.fileStatus.hashCode());
        result = prime * result + ((this.entityType == null) ? 0 : this.entityType.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("FileEntity (");

        sb.append(id);
        sb.append(", ").append(originalFileName);
        sb.append(", ").append(storageFileName);
        sb.append(", ").append(size);
        sb.append(", ").append(mimeType);
        sb.append(", ").append(insertDate);
        sb.append(", ").append(updateDate);
        sb.append(", ").append(path);
        sb.append(", ").append(fileType);
        sb.append(", ").append(fileStatus);
        sb.append(", ").append(entityType);

        sb.append(")");
        return sb.toString();
    }
}