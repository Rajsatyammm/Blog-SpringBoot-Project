package com.satyam.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.satyam.service.IFileService;

@RestController
@RequestMapping("/api/file")
public class FileController {
    @Autowired
    private IFileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestBody MultipartFile image) {
        String message = null;
        try {
            message = fileService.uploadImage(path, image);
        } catch (IOException e) {
            e.printStackTrace();
            message = "unable to upload file";
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
}
