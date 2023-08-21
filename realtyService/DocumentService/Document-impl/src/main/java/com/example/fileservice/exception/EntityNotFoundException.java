package com.example.fileservice.exception;

public class EntityNotFoundException extends ServiceException{
    public EntityNotFoundException(String entityId) {
        super(String.format("Entity with id %s not found",entityId));
    }
}
