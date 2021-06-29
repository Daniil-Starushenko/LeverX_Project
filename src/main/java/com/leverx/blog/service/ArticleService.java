package com.leverx.blog.service;

import com.leverx.blog.model.entity.Article;
import com.leverx.blog.model.entity.TagValue;
import com.leverx.blog.model.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ArticleService {

    Article saveArticle(Article article);

    Article getArticle(Integer id);

    Page<Article> findArticlesOnPage(int pageNumber, int pageLimit);

    Page<Article> findUsersArticlesOnPage(int pageNumber, int pageLimit, User user);

    Page<Article> findArticlesByTags(List<TagValue> tags, Integer page, Integer pageLimit);

    long articleCount();

    long countUsersArticles(User user);

    long countByTags(List<TagValue> tags);

    void updateArticle(Article article);

    void deleteArticle(Integer id);

    long countArticlesByEachTag();

}
