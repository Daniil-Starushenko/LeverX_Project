package com.leverx.blog.model.dto;

import com.leverx.blog.model.entity.ArticleStatus;
import lombok.Data;

@Data
public class RequestArticleDto {
    private String title;
    private String text;
    private ArticleStatus status;
}
