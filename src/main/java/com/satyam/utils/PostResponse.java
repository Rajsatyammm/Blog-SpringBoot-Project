package com.satyam.utils;

import java.util.ArrayList;
import java.util.List;

import com.satyam.dto.PostDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private List<PostDto> content = new ArrayList<>();
    private Integer pageNo;
    private Integer pageSize;
    private Integer totalPage;
    private Long totalElements;
    private Boolean isLastPage;
    private Boolean isFirstPage;
}
