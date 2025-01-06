package com.satyam.dto;

import java.sql.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDto {
    private Integer postId;

    @NotEmpty
    private String title;
    private String imageUrl;
    private Date addedAt;
    private Date updatedAt;

    @NotEmpty
    @Size(max = 10000, message = "max length of content is 10000")
    private String content;

    private UserDto userDto;
    private CategoryDto categoryDto;
}
