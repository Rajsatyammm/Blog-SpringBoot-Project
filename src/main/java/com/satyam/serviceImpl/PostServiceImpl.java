package com.satyam.serviceImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.satyam.utils.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.satyam.dto.PostDto;
import com.satyam.exceptions.CustomException;
import com.satyam.model.Category;
import com.satyam.model.Post;
import com.satyam.model.User;
import com.satyam.repository.ICategoryRepository;
import com.satyam.repository.IPostRepository;
import com.satyam.repository.IUserRepository;
import com.satyam.service.IPostService;
import com.satyam.utils.PostsResponse;

@Service
public class PostServiceImpl implements IPostService {

    @Autowired
    private IPostRepository postRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ApiResponse createPost(PostDto postDto, Integer userId, Integer categoryId, String imageUrl) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("User not found with id " + userId, HttpStatus.NOT_FOUND,
                        false));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CustomException("Category not found with id " + categoryId,
                        HttpStatus.NOT_FOUND, false));
        Post post = modelMapper.map(postDto, Post.class);
        post.setCategory(category);
        post.setUser(user);
        post.setImageUrl(imageUrl);
        post.setAddedAt(Date.valueOf(LocalDate.now()));
        post.setUpdatedAt(Date.valueOf(LocalDate.now()));
        Post createdPost = postRepository.save(post);
        return new ApiResponse("success", 201, true, modelMapper.map(createdPost, PostDto.class));
    }

    @Override
    public ApiResponse getPostsById(Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException("Post not found with id " + postId,
                        HttpStatus.NOT_FOUND,
                        false));
        return new ApiResponse("success", 200, true, modelMapper.map(post, PostDto.class));
    }

    @Override
    public ApiResponse getAllPostsByUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("User not found with id " + userId,
                        HttpStatus.NOT_FOUND,
                        false));
        ApiResponse apiResponse = new ApiResponse("success", 200, true, null);
        List<PostDto> postDto = user
                .getUserPosts()
                .stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .toList();
        apiResponse.setData(postDto);
        return apiResponse;
    }

    @Override
    public ApiResponse getAllPostsByCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CustomException("User not found with id " + categoryId, HttpStatus.NOT_FOUND,
                        false));
        ApiResponse apiResponse = new ApiResponse("success", 200, true, null);
        List<PostDto> postDto = category
                .getPosts()
                .stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .toList();
        apiResponse.setData(postDto);
        return apiResponse;
    }

    @Override
    public ApiResponse deletePostById(Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException("Post not found with id " + postId, HttpStatus.NOT_FOUND,
                        false));
        postRepository.delete(post);
        return new ApiResponse("success", 201, true, modelMapper.map(post, PostDto.class));
    }

    @Override
    public ApiResponse updatePost(PostDto postDto) {
        postRepository.findById(postDto.getPostId())
                .orElseThrow(() -> new CustomException("Post not found with id " + postDto.getPostId(),
                        HttpStatus.NOT_FOUND, false));
        postDto.setUpdatedAt(Date.valueOf(LocalDate.now()));
        Post updatedPost = postRepository.save(modelMapper.map(postDto, Post.class));
        return new ApiResponse("success", 201, true, modelMapper.map(updatedPost, PostDto.class));
    }

    @Override
    public PostsResponse getAllPosts(Integer pageNo, Integer pageSize, String sortBy, Boolean asc) {
        // Sort object
        Sort sort = asc ? Sort.by(Direction.ASC, sortBy) : Sort.by(Direction.DESC, sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> page = postRepository.findAll(pageable);
        List<PostDto> response = page.getContent()
                .stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
        PostsResponse postResponse = new PostsResponse();
        postResponse.setContent(response);
        postResponse.setPageNo(page.getNumber());
        postResponse.setPageSize(page.getSize());
        postResponse.setTotalPage(page.getTotalPages());
        postResponse.setIsLastPage(page.isLast());
        postResponse.setIsFirstPage(page.isFirst());
        postResponse.setTotalElements(page.getTotalElements());
        return postResponse;
    }

    @Override
    public ApiResponse searchPostsByTitle(String title) {
        List<Post> posts = postRepository.findByTitleContaining(title);
        ApiResponse apiResponse = new ApiResponse("success", 200, true, null);
        List<PostDto> postDto = posts
                .stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .toList();
        apiResponse.setData(postDto);
        return apiResponse;
    }

}
