package com.leverx.blog.service.impl;

import com.leverx.blog.model.dto.ArticleDto;
import com.leverx.blog.model.entity.Article;
import com.leverx.blog.repository.mysql.ArticleRepository;
import com.leverx.blog.service.ArticleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;

    @Override
    public Article saveArticle(Article article) {
        log.info("save article with id: {}", article.getId());
        return articleRepository.save(article);
    }

    @Override
    public Article updateArticle(Article article, ArticleDto articleDto) {
        return null;
    }
}
