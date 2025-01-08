package com.satyam.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.satyam.dto.UserDto;
import com.satyam.exceptions.CustomException;
import com.satyam.model.User;
import com.satyam.repository.IUserRepository;
import com.satyam.service.IUserService;
import com.satyam.utils.UserResponse;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserResponse getUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User not found with Id " + id, HttpStatus.NOT_FOUND, false));
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public List<UserResponse> getAllUsers(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<User> page = userRepository.findAll(pageable);
        List<User> allUsers = page.getContent();
        return allUsers
                .stream()
                .map(user -> modelMapper.map(user, UserResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse deleteUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User not found with Id " + id, HttpStatus.NOT_FOUND, false));
        userRepository.delete(user);
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse updateUser(UserDto userDto) {
        userRepository
                .findById(userDto.getId())
                .orElseThrow(
                        () -> new CustomException("User not found with Id " + userDto.getId(), HttpStatus.NOT_FOUND,
                                false));
        User user = userRepository.save(modelMapper.map(userDto, User.class));
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse createUser(UserDto userDto) {
        User user = userRepository.save(modelMapper.map(userDto, User.class));
        return modelMapper.map(user, UserResponse.class);
    }
}
