package com.satyam.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.satyam.dto.CategoryDto;
import com.satyam.exceptions.CustomException;
import com.satyam.model.Category;
import com.satyam.repository.ICategoryRepository;
import com.satyam.service.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto getCategoryById(Integer id) {
        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isPresent())
            return modelMapper.map(optional.get(), CategoryDto.class);
        throw new CustomException("Category not found by Id " + id, "404", false);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        return categoryRepository
                .findAll()
                .stream()
                .map((category) -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public String deleteCategoryById(Integer id) {
        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isPresent()) {
            categoryRepository.delete(optional.get());
            return "Category deleted with id " + optional.get().getCategoryId();
        }
        throw new CustomException("Category not found by Id " + id, "404", false);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        Optional<Category> optional = categoryRepository.findById(categoryDto.getCategoryId());
        if (optional.isPresent()) {
            Category updatedCategory = categoryRepository.save(modelMapper.map(categoryDto, Category.class));
            return modelMapper.map(updatedCategory, CategoryDto.class);
        }
        throw new CustomException("Category not found by Id " + categoryDto.getCategoryId(), "404", false);
    }

    @Override
    public String createCategory(CategoryDto categoryDto) {
        Category savedCategory = categoryRepository.save(modelMapper.map(categoryDto, Category.class));
        return "New Category created with id " + savedCategory.getCategoryId();
    }
}
