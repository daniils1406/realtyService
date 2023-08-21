package com.example.fileservice.exception;

public class NotYourDocumentException extends ServiceException{
    public NotYourDocumentException() {
        super("You cant change documents that not yours!");
    }
}
