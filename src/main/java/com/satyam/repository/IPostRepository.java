package com.satyam.repository;

import java.util.List;
import java.util.Locale.Category;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satyam.model.Post;
import com.satyam.model.User;

public interface IPostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);

    List<Category> findByCategory(Category category);

    List<Post> findByTitleContaining(String title);
}
