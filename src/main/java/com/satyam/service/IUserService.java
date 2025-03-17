package com.satyam.service;

import com.satyam.dto.UserDto;
import com.satyam.utils.ApiResponse;

public interface IUserService {

    ApiResponse getUserById(Integer id);

    ApiResponse getAllUsers(Integer pageNo, Integer maxPage);

    ApiResponse deleteUserById(Integer id);

    ApiResponse updateUser(UserDto user);

    ApiResponse createUser(UserDto user);
}
