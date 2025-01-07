package com.satyam.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satyam.dto.CategoryDto;
import com.satyam.service.ICategoryService;
import com.satyam.utils.CategoryResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @PostMapping("/save")
    public ResponseEntity<CategoryResponse> createUser(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryResponse response = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CategoryResponse> getUserById(@PathVariable Integer id) {
        CategoryResponse response = categoryService.getCategoryById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryResponse>> getAllUsers() {
        return new ResponseEntity<>(categoryService.getAllCategory(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CategoryResponse> deleteUserById(@PathVariable Integer id) {
        CategoryResponse response = categoryService.deleteCategoryById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
