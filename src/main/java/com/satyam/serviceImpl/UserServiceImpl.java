package com.satyam.serviceImpl;

import java.util.List;
import java.util.Optional;

import com.satyam.utils.ApiResponse;
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

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ApiResponse getUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User not found with Id " + id, HttpStatus.NOT_FOUND, false));
        return new ApiResponse("success", 200, true, modelMapper.map(user, UserDto.class));
    }

    @Override
    public ApiResponse getAllUsers(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<User> page = userRepository.findAll(pageable);
        List<UserDto> allUsers = page.getContent()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .toList();
        return new ApiResponse("success", 200, true, allUsers);
    }

    @Override
    public ApiResponse deleteUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User not found with Id " + id, HttpStatus.NOT_FOUND, false));
        userRepository.delete(user);
        return new ApiResponse("success", 200, true, modelMapper.map(user, UserDto.class));
    }

    @Override
    public ApiResponse updateUser(UserDto userDto) {
        userRepository
                .findById(userDto.getId())
                .orElseThrow(
                        () -> new CustomException("User not found with Id " + userDto.getId(), HttpStatus.NOT_FOUND,
                                false));
        User user = userRepository.save(modelMapper.map(userDto, User.class));
        return new ApiResponse("success", 200, true, modelMapper.map(user, UserDto.class));
    }

    @Override
    public ApiResponse createUser(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if(optionalUser.isPresent())
            throw new CustomException("User already exists", HttpStatus.CONFLICT, false);
        User user = userRepository.save(modelMapper.map(userDto, User.class));
        return new ApiResponse("success", 201, true, modelMapper.map(user, UserDto.class));
    }
}
