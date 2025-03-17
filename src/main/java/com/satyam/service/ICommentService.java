package com.satyam.service;

import com.satyam.dto.CommentDto;
import com.satyam.utils.ApiResponse;

public interface ICommentService {
    ApiResponse addComment(CommentDto comment, Integer userId, Integer postId);

    ApiResponse getAllCommentsOnPost(Integer postId);

    ApiResponse deleteComment(Integer commentId);
}
