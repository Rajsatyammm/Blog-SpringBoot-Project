package com.satyam.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponse> handleCustomException(CustomException e) {
        return new ResponseEntity<>(new ExceptionResponse(e.getMessage(), e.getStatusCode().toString(), false),
                e.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleAllOtherException(Exception e) {
        return new ResponseEntity<>(
                new ExceptionResponse(e.getMessage(), "404", false),
                HttpStatus.NOT_FOUND);
    }
}
