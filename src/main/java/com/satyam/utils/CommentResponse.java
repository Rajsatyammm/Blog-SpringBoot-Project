package com.satyam.utils;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.satyam.dto.PostDto;
import com.satyam.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    private String content;

    @JsonBackReference
    private UserDto user;

    @JsonBackReference
    private PostDto post;
}
