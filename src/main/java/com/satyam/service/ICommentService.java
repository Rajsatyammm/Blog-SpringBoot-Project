package com.satyam.service;

import java.util.List;

import com.satyam.dto.CommentDto;

public interface ICommentService {
    CommentDto addComment(CommentDto comment, Integer userId, Integer postId);

    List<CommentDto> getAllCommentsOnPost(Integer postId);

    String deleteComment(Integer commentId);
}
