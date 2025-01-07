package com.satyam.utils;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    private String content;

    @JsonBackReference
    private UserResponse user;

    @JsonBackReference
    private PostResponse post;
}
