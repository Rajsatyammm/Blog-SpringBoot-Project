package com.satyam.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
    private Integer commentId;

    @NotEmpty
    @Size(min = 3, message = "content size must be minimum of 3 character")
    private String content;
    private UserDto user;
    private PostDto post;
}
