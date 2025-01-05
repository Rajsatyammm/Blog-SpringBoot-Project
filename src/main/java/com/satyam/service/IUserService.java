package com.satyam.service;

import java.util.List;

import com.satyam.dto.UserDto;

public interface IUserService {

    UserDto getUserById(Integer id);

    List<UserDto> getAllUsers(Integer pageNo, Integer maxPage);

    String deleteUserById(Integer id);

    UserDto updateUser(UserDto user);

    String createUser(UserDto user);
}
