package com.satyam.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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

    private UserDto user;
    private CategoryDto category;
    private List<CommentDto> commentsList = new ArrayList<>();
}
