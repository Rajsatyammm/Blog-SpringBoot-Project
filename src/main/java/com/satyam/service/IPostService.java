package com.satyam.service;

import java.util.List;

import com.satyam.dto.PostDto;
import com.satyam.utils.PostResponse;
import com.satyam.utils.PostsResponse;

public interface IPostService {

    PostResponse createPost(PostDto postDto, Integer userId, Integer categoryId, String imageUrl);

    PostResponse getPostsById(Integer postId);

    List<PostResponse> getAllPostsByUser(Integer userId);

    List<PostResponse> getAllPostsByCategory(Integer categoryId);

    PostsResponse getAllPosts(Integer pageNo, Integer pageSize, String sortBy, Boolean asc);

    PostResponse deletePostById(Integer postId);

    PostResponse updatePost(PostDto postDto);

    List<PostResponse> searchPostsByTitle(String title);

}
