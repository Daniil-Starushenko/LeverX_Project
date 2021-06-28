package com.leverx.blog.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CommentsPageDto {
    private Integer page;
    private Integer pageLimit;
    private long totalRecords;
    private List<CommentDto> comments;
}
