package com.leverx.blog.model.dto;

import lombok.Data;

import java.util.Set;

@Data
public class TagDto {
    private Integer id;
    private TagValueDto tagValue;
    private Set<ArticleDto> articles;
}
