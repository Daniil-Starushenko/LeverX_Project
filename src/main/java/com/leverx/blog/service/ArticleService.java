package com.leverx.blog.service;

import com.leverx.blog.model.dto.ArticleDto;
import com.leverx.blog.model.dto.RequestArticleDto;
import com.leverx.blog.model.entity.Article;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ArticleService {

    Article saveArticle(Article article);

    Article updateArticle(Article article, RequestArticleDto articleDto);

    Page<Article> findArticlesOnPage(int pageNumber, int pageLimit);

    long articleCount();

    void updateArticle(Article article);

    Article getArticle(Integer id);

}
