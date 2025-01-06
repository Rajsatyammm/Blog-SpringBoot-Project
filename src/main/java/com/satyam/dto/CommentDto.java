package com.satyam.dto;

import lombok.Data;

@Data
public class CommentDto {
    private Integer commentId;
    private String content;
    private UserDto userDto;
    private PostDto postDto;
}
