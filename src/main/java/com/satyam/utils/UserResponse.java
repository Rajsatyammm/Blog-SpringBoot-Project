package com.satyam.utils;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.satyam.dto.CommentDto;
import com.satyam.dto.PostDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String fullName;
    private String email;

    @JsonManagedReference
    private List<PostDto> userPosts = new ArrayList<>();

    @JsonManagedReference
    private List<CommentDto> comments = new ArrayList<>();
}
