package com.leverx.blog.service;

import com.leverx.blog.model.dto.ArticleDto;
import com.leverx.blog.model.entity.Article;
import org.springframework.data.domain.Page;

public interface ArticleService {

    Article saveArticle(Article article);

    Article updateArticle(Article article, ArticleDto articleDto);

    Page<Article> findArticlesOnPage(int pageNumber, int pageLimit);

    long articleCount();

}
