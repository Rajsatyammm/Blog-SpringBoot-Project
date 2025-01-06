package com.satyam.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Integer categoryId;

    @NotNull
    @Size(min = 3, message = "Category Name must be min of 3 chars")
    private String categoryName;

    private List<PostDto> posts = new ArrayList<>();
}
