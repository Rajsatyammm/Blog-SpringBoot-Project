package com.satyam.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomException extends RuntimeException {
    private String message;
    private HttpStatus statusCode;
    private Boolean success;

    public CustomException(String message, HttpStatus statusCode, Boolean success) {
        super(message);
        this.message = message;
        this.statusCode = statusCode;
        this.success = success;
    }
}
