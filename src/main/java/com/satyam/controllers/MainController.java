package com.satyam.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.satyam.utils.ApiResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class MainController {
    
    @GetMapping("/")
    public ResponseEntity<ApiResponse> getIndex() {
        return new ResponseEntity<>(new ApiResponse("Everything is alright!!", 200, true, null), HttpStatus.OK);
    }

    @GetMapping("/health")
    public ResponseEntity<ApiResponse> healthCheck() {
        return new ResponseEntity<>(new ApiResponse("server is up and running", 200, true, null), HttpStatus.OK);
    }
    
}
