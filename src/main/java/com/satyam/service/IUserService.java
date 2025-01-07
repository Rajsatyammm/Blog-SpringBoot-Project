package com.satyam.service;

import java.util.List;

import com.satyam.dto.UserDto;
import com.satyam.utils.UserResponse;

public interface IUserService {

    UserResponse getUserById(Integer id);

    List<UserResponse> getAllUsers(Integer pageNo, Integer maxPage);

    UserResponse deleteUserById(Integer id);

    UserResponse updateUser(UserDto user);

    UserResponse createUser(UserDto user);
}
