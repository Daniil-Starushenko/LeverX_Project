package com.leverx.blog.model.dto;

import com.leverx.blog.model.entity.ArticleStatus;
import com.leverx.blog.model.entity.TagValue;
import lombok.Data;

import java.util.List;

@Data
public class RequestArticleDto {
    private String title;
    private String text;
    private ArticleStatus status;
    private List<TagValue> tags;
}
