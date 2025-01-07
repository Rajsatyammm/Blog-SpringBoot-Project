package com.satyam.service;

import java.util.List;

import com.satyam.dto.PostDto;
import com.satyam.utils.PostsResponse;

public interface IPostService {

    PostsResponse createPost(PostDto postDto, Integer userId, Integer categoryId, String imageUrl);

    PostsResponse getPostsById(Integer postId);

    List<PostsResponse> getAllPostsByUser(Integer userId);

    List<PostsResponse> getAllPostsByCategory(Integer categoryId);

    PostsResponse getAllPosts(Integer pageNo, Integer pageSize, String sortBy, Boolean asc);

    PostsResponse deletePostById(Integer postId);

    PostsResponse updatePost(PostDto postDto);

    List<PostsResponse> searchPostsByTitle(String title);

}
