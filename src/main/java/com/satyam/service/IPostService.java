package com.satyam.service;

import com.satyam.dto.PostDto;
import com.satyam.utils.ApiResponse;
import com.satyam.utils.PostsResponse;

public interface IPostService {

    ApiResponse createPost(PostDto postDto, Integer userId, Integer categoryId, String imageUrl);

    ApiResponse getPostsById(Integer postId);

    ApiResponse getAllPostsByUser(Integer userId);

    ApiResponse getAllPostsByCategory(Integer categoryId);

    PostsResponse getAllPosts(Integer pageNo, Integer pageSize, String sortBy, Boolean asc);

    ApiResponse deletePostById(Integer postId);

    ApiResponse updatePost(PostDto postDto);

    ApiResponse searchPostsByTitle(String title);

}
