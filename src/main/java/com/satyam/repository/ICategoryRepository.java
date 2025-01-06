package com.satyam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satyam.model.Category;

public interface ICategoryRepository extends JpaRepository<Category, Integer> {

}
