package com.satyam.utils;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private String title;
    private String content;
    private String imageUrl;
    private Date addedAt;
    private Date updatedAt;

    @JsonBackReference
    private UserResponse user;

    @JsonBackReference
    private CategoryResponse category;

    @JsonManagedReference
    private List<CommentResponse> commentsList;
}
