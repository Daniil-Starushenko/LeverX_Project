package com.leverx.blog.repository.mysql;

import com.leverx.blog.model.entity.Article;
import com.leverx.blog.model.entity.ArticleStatus;
import com.leverx.blog.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    Optional<Article> findArticleById(Integer id);

    Page<Article> findAllByStatus (ArticleStatus status, Pageable pageable);

    long countAllByStatus(ArticleStatus status);

    Page<Article> findAllByUser(User user, Pageable pageable);

    long countAllByUser(User user);
}
