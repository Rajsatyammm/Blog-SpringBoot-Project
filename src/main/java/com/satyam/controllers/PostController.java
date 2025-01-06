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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.satyam.constants.AppConstants;
import com.satyam.dto.PostDto;
import com.satyam.service.IPostService;
import com.satyam.utils.PostResponse;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private IPostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/save")
    public ResponseEntity<String> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
            @PathVariable Integer categoryId,
            @RequestParam(name = "imageUrl", defaultValue = "default.png", required = false) String imageUrl) {
        String message = postService.createPost(postDto, userId, categoryId, imageUrl);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/posts/user/{userId}")
    public ResponseEntity<List<PostDto>> getAllPostByUserId(@PathVariable Integer userId) {
        return new ResponseEntity<>(postService.getAllPostsByUser(userId), HttpStatus.OK);
    }

    @GetMapping("/posts/category/{categoryId}")
    public ResponseEntity<List<PostDto>> getAllPostByCategory(@PathVariable Integer categoryId) {
        return new ResponseEntity<>(postService.getAllPostsByCategory(categoryId), HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
        return new ResponseEntity<>(postService.getPostsById(postId), HttpStatus.OK);
    }

    @GetMapping("/posts/getAll")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(name = "pageNo", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(name = "asc", defaultValue = AppConstants.ASC, required = false) Boolean asc) {
        return new ResponseEntity<>(postService.getAllPosts(pageNo, pageSize, sortBy, asc), HttpStatus.OK);
    }

    @GetMapping("/posts/search")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@RequestParam String query) {
        return new ResponseEntity<>(postService.searchPostsByTitle(query), HttpStatus.OK);
    }

}
