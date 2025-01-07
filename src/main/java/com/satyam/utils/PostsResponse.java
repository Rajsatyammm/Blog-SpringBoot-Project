package com.satyam.utils;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostsResponse {
    private List<PostResponse> content = new ArrayList<>();
    private Integer pageNo;
    private Integer pageSize;
    private Integer totalPage;
    private Long totalElements;
    private Boolean isLastPage;
    private Boolean isFirstPage;
}
