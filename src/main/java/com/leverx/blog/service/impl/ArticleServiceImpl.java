package com.leverx.blog.service.impl;

import com.leverx.blog.exception.entity.EntityNotFoundException;
import com.leverx.blog.exception.entity.InvalidateArgumentException;
import com.leverx.blog.model.entity.*;
import com.leverx.blog.repository.mysql.ArticleRepository;
import com.leverx.blog.repository.mysql.TagRepository;
import com.leverx.blog.service.ArticleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;
    private TagRepository tagRepository;

    @Override
    @Transactional(readOnly = true)
    public Article getArticle(Integer id) {
        log.info("try to find article with id {}", id);
        return articleRepository.findArticleById(id)
                .orElseThrow(() -> new EntityNotFoundException("article with id: " + id + "is not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Article> findArticlesOnPage(int pageNumber, int pageLimit) {
        checkPages(pageNumber, pageLimit);
        PageRequest pageRequest = PageRequest.of(pageNumber - 1,
                pageLimit);
        return articleRepository.findAllByStatus(ArticleStatus.PUBLIC ,pageRequest);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Article> findUsersArticlesOnPage(int pageNumber, int pageLimit, User user) {
        checkPages(pageNumber, pageLimit);
        PageRequest pageRequest = PageRequest.of(pageNumber - 1,
                pageLimit);
        return articleRepository.findAllByUser(user, pageRequest);
    }

    @Override
    public Page<Article> findArticlesByTags(List<TagValue> tagValues, Integer page, Integer pageLimit) {
        List<Tag> tags = new ArrayList<>();
        tagValues.forEach(
                tagValue -> tags.add(tagRepository.getTagByTagValue(tagValue))
        );
        checkPages(page, pageLimit);
        PageRequest pageRequest = PageRequest.of(page - 1,
                pageLimit);

        return articleRepository.findDistinctByTagsIn(tags, pageRequest);
    }

    private void checkPages(int pageNumber, int pageLimit) {
        if (pageNumber <= 0 || pageLimit <= 0 || pageLimit > 10) {
            throw new InvalidateArgumentException("Page starts from 1. Provided: "
                    + pageNumber + ". Page limit minimal value is 1. Provided: "
                    + pageLimit);
        }
    }

    @Override
    public Article saveArticle(Article article) {
        log.info("save article with id: {}", article.getId());
        return articleRepository.save(article);
    }

    @Override
    @Transactional(readOnly = true)
    public long articleCount() {
        return articleRepository.countAllByStatus(ArticleStatus.PUBLIC);
    }

    @Override
    @Transactional(readOnly = true)
    public long countUsersArticles(User user) {
        return articleRepository.countAllByUser(user);
    }

    @Override
    public void updateArticle(Article article) {
        articleRepository.save(article);
    }

    @Override
    public void deleteArticle(Integer id) {
            articleRepository.deleteById(id);
    }

}
