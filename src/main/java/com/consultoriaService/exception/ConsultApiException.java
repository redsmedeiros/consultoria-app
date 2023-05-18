package com.consultoriaService.exception;

import org.springframework.http.HttpStatus;

public class ConsultApiException extends RuntimeException {

    private HttpStatus httpStatus;
    private String message;

    public ConsultApiException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public ConsultApiException(String message, HttpStatus httpStatus, String message1) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message1;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
