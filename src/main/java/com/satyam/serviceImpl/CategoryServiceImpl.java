package com.satyam.serviceImpl;

import com.satyam.utils.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.satyam.dto.CategoryDto;
import com.satyam.exceptions.CustomException;
import com.satyam.model.Category;
import com.satyam.repository.ICategoryRepository;
import com.satyam.service.ICategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ApiResponse getCategoryById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(
                        () -> new CustomException("Category not found with id " + id, HttpStatus.NOT_FOUND, false));
        return new ApiResponse("success", 201, true, modelMapper.map(category, CategoryDto.class));
    }

    @Override
    public ApiResponse getAllCategory() {
        ApiResponse response = new ApiResponse("success", 200, true, null);
        List<CategoryDto> categoryDtoList = categoryRepository
                .findAll()
                .stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .toList();
        response.setData(categoryDtoList);
        return response;
    }

    @Override
    public ApiResponse deleteCategoryById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CustomException("Category not found with id " + id, HttpStatus.NOT_FOUND, false));
        categoryRepository.delete(category);
        return new ApiResponse("success", 201, true, modelMapper.map(category, CategoryDto.class));
    }

    @Override
    public ApiResponse updateCategory(CategoryDto categoryDto) {
        categoryRepository.findById(categoryDto.getCategoryId())
                .orElseThrow(() -> new CustomException("Category not found with id " + categoryDto.getCategoryId(),
                        HttpStatus.NOT_FOUND, false));
        Category updatedCategory = categoryRepository.save(modelMapper.map(categoryDto, Category.class));
        return new ApiResponse("success", 201, true, modelMapper.map(updatedCategory, CategoryDto.class));
    }

    @Override
    public ApiResponse createCategory(CategoryDto categoryDto) {
        Category savedCategory = categoryRepository.save(modelMapper.map(categoryDto, Category.class));
        return new ApiResponse("success", 201, true, modelMapper.map(savedCategory, CategoryDto.class));
    }
}
