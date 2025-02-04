package com.juliana.email_service.core.exeptions;

public class EmailServiceExeption extends RuntimeException {

    public EmailServiceExeption(String message){
        super(message);
    }

    public EmailServiceExeption(String message, Throwable cause){
        super(message, cause);
    }

}
