package com.satyam.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satyam.dto.CommentDto;
import com.satyam.service.ICommentService;
import com.satyam.utils.CommentResponse;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @PostMapping("/user/{userId}/post/{postId}/save")
    public ResponseEntity<CommentResponse> addComment(@RequestBody CommentDto commentDto, @PathVariable Integer userId,
            @PathVariable Integer postId) {
        CommentResponse savedComment = commentService.addComment(commentDto, userId, postId);
        return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
    }

    @GetMapping("/comments/post/{postId}")
    public ResponseEntity<List<CommentResponse>> getAllCommentsOnPost(@PathVariable Integer postId) {
        List<CommentResponse> commentsList = commentService.getAllCommentsOnPost(postId);
        return new ResponseEntity<>(commentsList, HttpStatus.CREATED);
    }

}