package com.satyam.exceptions.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.satyam.exceptions.CustomException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomException> handleCustomException(CustomException e) {
        return new ResponseEntity<>(new CustomException(e.getMessage(), e.getStatusCode().toString(), false),
                HttpStatus.NOT_FOUND);
    }
}
