package com.satyam.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomException extends RuntimeException {
    private String message;
    private String statusCode;
    private Boolean success;

    public CustomException(String message, String statusCode, Boolean success) {
        super(message + "statusCode " + statusCode + " success " + success);
    }
}
