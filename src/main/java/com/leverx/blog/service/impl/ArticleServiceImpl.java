package com.leverx.blog.service.impl;

import com.leverx.blog.exception.entity.EntityNotFoundException;
import com.leverx.blog.exception.entity.InvalidateArgumentException;
import com.leverx.blog.model.dto.ArticleDto;
import com.leverx.blog.model.dto.RequestArticleDto;
import com.leverx.blog.model.entity.Article;
import com.leverx.blog.model.entity.ArticleStatus;
import com.leverx.blog.model.entity.Tag;
import com.leverx.blog.model.entity.TagValue;
import com.leverx.blog.repository.mysql.ArticleRepository;
import com.leverx.blog.service.ArticleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;

    @Override
    public Article getArticle(Integer id) {
        Article article = articleRepository.findArticleById(id)
                .orElseThrow(() -> new EntityNotFoundException("article with id: " + id + "is not found"));

        return article;
    }

    @Override
    public Article saveArticle(Article article) {
        log.info("save article with id: {}", article.getId());
        return articleRepository.save(article);
    }

    @Override
    public Article updateArticle(Article article, RequestArticleDto articleDto) {
        return null;
    }


    @Override
    public Page<Article> findArticlesOnPage(int pageNumber, int pageLimit) {
        if (pageNumber <= 0 || pageLimit <= 0 || pageLimit > 10) {
            throw new InvalidateArgumentException("Page starts from 1. Provided: "
                    + pageNumber + ". Page limit minimal value is 1. Provided: "
                    + pageLimit);
        }
        PageRequest pageRequest = PageRequest.of(pageNumber - 1,
                pageLimit);
        return articleRepository.findAllByStatus(ArticleStatus.PUBLIC ,pageRequest);
    }

    @Override
    @Transactional(readOnly = true)
    public long articleCount() {
        return articleRepository.countAllByStatus(ArticleStatus.PUBLIC);
    }

    @Override
    public void updateArticle(Article article) {
        articleRepository.save(article);
    }

}
