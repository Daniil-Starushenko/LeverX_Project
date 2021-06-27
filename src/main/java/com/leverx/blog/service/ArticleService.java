package com.leverx.blog.service;

import com.leverx.blog.model.dto.ArticleDto;
import com.leverx.blog.model.entity.Article;

public interface ArticleService {

    Article saveArticle(Article article);

    Article updateArticle(Article article, ArticleDto articleDto);

}
