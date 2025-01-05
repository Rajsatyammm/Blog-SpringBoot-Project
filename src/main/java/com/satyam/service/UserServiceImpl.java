package com.satyam.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.satyam.dto.UserDto;
import com.satyam.exceptions.CustomException;
import com.satyam.model.User;
import com.satyam.repository.IUserRepository;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto getUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User not found but Id " + id, "404", false));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<User> page = userRepository.findAll(pageable);
        List<User> allUsers = page.getContent();
        return allUsers
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public String deleteUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User not found but Id " + id, "404", false));
        userRepository.delete(user);
        return "User with id " + id + " deleted successfully";
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        userRepository.findById(userDto.getId())
                .orElseThrow(() -> new CustomException("User not found but Id " + userDto.getId(), "404", false));
        User user = userRepository.save(modelMapper.map(userDto, User.class));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public String createUser(UserDto userDto) {
        Optional<User> optional = userRepository.findById(userDto.getId());
        if (optional.isPresent())
            throw new CustomException("User already found by id " + userDto.getId(), "", false);
        User user = userRepository.save(modelMapper.map(userDto, User.class));
        return "New User created with id " + user.getId();
    }
}
