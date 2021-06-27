package com.leverx.blog.model.dto;

import com.leverx.blog.model.entity.TagValue;
import lombok.Data;

import java.util.Set;

@Data
public class TagDto {
    private Integer id;
    private TagValue tagValue;
    private Set<ArticleDto> articles;
}
