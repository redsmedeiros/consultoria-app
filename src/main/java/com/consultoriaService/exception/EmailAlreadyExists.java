package com.consultoriaService.exception;

public class EmailAlreadyExists extends  RuntimeException{

    private String message;

    public EmailAlreadyExists(String message){
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
