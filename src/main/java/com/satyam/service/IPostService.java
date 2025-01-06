package com.satyam.service;

import java.util.List;

import com.satyam.dto.PostDto;
import com.satyam.utils.PostResponse;

public interface IPostService {

    String createPost(PostDto postDto, Integer userId, Integer categoryId, String imageUrl);

    PostDto getPostsById(Integer postId);

    List<PostDto> getAllPostsByUser(Integer userId);

    List<PostDto> getAllPostsByCategory(Integer categoryId);

    PostResponse getAllPosts(Integer pageNo, Integer pageSize, String sortBy, Boolean asc);

    String deletePostById(Integer postId);

    PostDto updatePost(PostDto postDto);

    List<PostDto> searchPostsByTitle(String title);

}
