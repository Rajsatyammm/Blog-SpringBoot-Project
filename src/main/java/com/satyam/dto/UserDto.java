package com.satyam.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
    private Integer id;

    @NotEmpty
    @Size(min = 5, message = "Name length must be at least 5 chars")
    private String fullName;

    @Email(message = "Email not valid")
    private String email;

    @NotEmpty
    @Size(min = 6, max = 10, message = "password must be min 6 and max 10 char")
    private String password;
    private List<PostDto> userPosts = new ArrayList<>();
}
