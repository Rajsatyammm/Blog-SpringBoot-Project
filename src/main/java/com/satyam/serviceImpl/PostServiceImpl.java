package com.satyam.serviceImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
    public PostsResponse createPost(PostDto postDto, Integer userId, Integer categoryId, String imageUrl) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("User not found with id " + userId, "404", false));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CustomException("Category not found with id " + categoryId,
                        "404", false));
        Post post = modelMapper.map(postDto, Post.class);
        post.setCategory(category);
        post.setUser(user);
        post.setImageUrl(imageUrl);
        post.setAddedAt(Date.valueOf(LocalDate.now()));
        post.setUpdatedAt(Date.valueOf(LocalDate.now()));
        Post createdPost = postRepository.save(post);
        return modelMapper.map(createdPost, PostsResponse.class);
    }

    @Override
    public PostsResponse getPostsById(Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException("Post not found with id " + postId, "404", false));
        return modelMapper.map(post, PostsResponse.class);
    }

    @Override
    public List<PostsResponse> getAllPostsByUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("User not found with id " + userId, "404", false));
        return user
                .getUserPosts()
                .stream()
                .map(post -> modelMapper.map(post, PostsResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostsResponse> getAllPostsByCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CustomException("User not found with id " + categoryId, "404", false));
        return category
                .getPosts()
                .stream()
                .map(post -> modelMapper.map(post, PostsResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostsResponse deletePostById(Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException("Post not found with id " + postId, "404", false));
        postRepository.delete(post);
        return modelMapper.map(post, PostsResponse.class);
    }

    @Override
    public PostsResponse updatePost(PostDto postDto) {
        postRepository.findById(postDto.getPostId())
                .orElseThrow(() -> new CustomException("Post not found with id " + postDto.getPostId(), "404", false));
        postDto.setUpdatedAt(Date.valueOf(LocalDate.now()));
        Post updatedPost = postRepository.save(modelMapper.map(postDto, Post.class));
        return modelMapper.map(updatedPost, PostsResponse.class);
    }

    @Override
    public PostsResponse getAllPosts(Integer pageNo, Integer pageSize, String sortBy, Boolean asc) {
        // Sort object
        Sort sort = asc ? Sort.by(Direction.ASC, sortBy) : Sort.by(Direction.DESC, sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> page = postRepository.findAll(pageable);
        List<PostDto> postDtos = page.getContent()
                .stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
        PostsResponse postResponse = new PostsResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNo(page.getNumber());
        postResponse.setPageSize(page.getSize());
        postResponse.setTotalPage(page.getTotalPages());
        postResponse.setIsLastPage(page.isLast());
        postResponse.setIsFirstPage(page.isFirst());
        postResponse.setTotalElements(page.getTotalElements());
        return postResponse;
    }

    @Override
    public List<PostsResponse> searchPostsByTitle(String title) {
        List<Post> posts = postRepository.findByTitleContaining(title);
        return posts
                .stream()
                .map(post -> modelMapper.map(post, PostsResponse.class))
                .collect(Collectors.toList());
    }

}
