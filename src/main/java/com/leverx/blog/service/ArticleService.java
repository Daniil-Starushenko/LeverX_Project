package com.leverx.blog.service;

import com.leverx.blog.model.entity.Article;
import com.leverx.blog.model.entity.User;
import org.springframework.data.domain.Page;

public interface ArticleService {

    Article saveArticle(Article article);

    Page<Article> findArticlesOnPage(int pageNumber, int pageLimit);

    Page<Article> findUsersArticlesOnPage(int pageNumber, int pageLimit, User user);

    long articleCount();

    long countUsersArticles(User user);

    void updateArticle(Article article);

    Article getArticle(Integer id);

    void deleteArticle(Integer id);

}
