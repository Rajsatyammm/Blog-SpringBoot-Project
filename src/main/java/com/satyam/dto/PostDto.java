package com.satyam.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDto {
    private Integer postId;

    @NotEmpty
    @Size(min = 3, message = "message must be minimum of 3 character")
    private String title;
    private String imageUrl;
    private Date addedAt;
    private Date updatedAt;

    @NotEmpty
    @Size(max = 10000, message = "max length of content is 10000")
    private String content;

    @JsonBackReference
    private UserDto user;

    @JsonManagedReference
    private CategoryDto category;

    @JsonManagedReference
    private List<CommentDto> commentsList = new ArrayList<>();
}
