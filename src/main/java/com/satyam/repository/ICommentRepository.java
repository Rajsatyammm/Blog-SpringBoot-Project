package com.satyam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satyam.model.Comment;

public interface ICommentRepository extends JpaRepository<Comment, Integer> {

}
