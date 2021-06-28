package com.leverx.blog.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.leverx.blog.model.entity.TagValue;
import lombok.Data;

import java.util.Set;

@Data
public class TagDto {
    private Integer id;
    private TagValue tagValue;

    @JsonBackReference
    private Set<ArticleDto> articles;
}
