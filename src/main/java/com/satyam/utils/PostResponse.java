package com.satyam.utils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "postId")
public class PostResponse {
    private Integer postId;
    private String title;
    private String content;
    private String imageUrl;
    private Date addedAt;
    private Date updatedAt;

    private UserResponse user;
    private CategoryResponse category;
    private List<CommentResponse> commentsList = new ArrayList<>();
}