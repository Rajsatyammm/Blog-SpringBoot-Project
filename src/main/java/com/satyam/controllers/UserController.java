package com.satyam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.satyam.dto.UserDto;
import com.satyam.service.IUserService;
import com.satyam.utils.ApiResponse;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponse> getAllUser(@RequestBody UserDto user,
            @RequestParam(required = false, value = "1") Integer pageNo,
            @RequestParam(required = false, value = "5") Integer pageSize) {
        String message = userService.createUser(user);
        return new ResponseEntity<>(new ApiResponse(message, 201, true), HttpStatus.CREATED);
    }
}
