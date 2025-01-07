package com.satyam.service;

import java.util.List;

import com.satyam.dto.CommentDto;
import com.satyam.utils.CommentResponse;

public interface ICommentService {
    CommentResponse addComment(CommentDto comment, Integer userId, Integer postId);

    List<CommentResponse> getAllCommentsOnPost(Integer postId);

    CommentResponse deleteComment(Integer commentId);
}
