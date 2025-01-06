package com.satyam.controllers;

import java.util.List;

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

@RestController
@RequestMapping("/api")
public class CommentController {

    private ICommentService commentService;

    @PostMapping("/user/{userId}/post/{postId}/save")
    public ResponseEntity<CommentDto> addComment(@RequestBody CommentDto commentDto, @PathVariable Integer userId,
            @PathVariable Integer postId) {
        CommentDto savedComment = commentService.addComment(commentDto, userId, postId);
        return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
    }

    @GetMapping("/comments/post/{postId}")
    public ResponseEntity<List<CommentDto>> getAllCommentsOnPost(@PathVariable Integer postId) {
        List<CommentDto> commentsList = commentService.getAllCommentsOnPost(postId);
        return new ResponseEntity<>(commentsList, HttpStatus.CREATED);
    }

}