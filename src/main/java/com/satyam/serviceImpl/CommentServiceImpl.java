package com.satyam.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.satyam.dto.CommentDto;
import com.satyam.exceptions.CustomException;
import com.satyam.model.Comment;
import com.satyam.model.Post;
import com.satyam.model.User;
import com.satyam.repository.ICommentRepository;
import com.satyam.repository.IPostRepository;
import com.satyam.repository.IUserRepository;
import com.satyam.service.ICommentService;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private ICommentRepository commentRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IPostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto addComment(CommentDto commentDto, Integer userId, Integer postId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("User not found by id " + userId, "404", false));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException("Post not found by id " + postId, "404", false));
        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment.setUser(user);
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        return modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public List<CommentDto> getAllCommentsOnPost(Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException("Post not found by id " + postId, "404", false));
        return post.getCommentsList()
                .stream()
                .map(comment -> modelMapper.map(comment, CommentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public String deleteComment(Integer commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException("Comment not found by id " + commentId, "404", false));
        commentRepository.delete(comment);
        return "Comment deleted with id " + commentId;
    }

}
