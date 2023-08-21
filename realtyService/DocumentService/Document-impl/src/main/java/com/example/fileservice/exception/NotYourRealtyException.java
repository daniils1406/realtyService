package com.example.fileservice.exception;

public class NotYourRealtyException extends ServiceException{
    public NotYourRealtyException() {
        super("You cant change image of realty that not yours");
    }
}
