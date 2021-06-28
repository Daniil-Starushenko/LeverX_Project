package com.leverx.blog.repository.mysql;

import com.leverx.blog.model.entity.Article;
import com.leverx.blog.model.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Optional<Comment> findById(Integer id);

    Optional<Comment> findByArticleIdAndId(Integer articleId, Integer commentId);

    Page<Comment> findAllByArticle(Article article, Pageable pageable);

    long countByArticle(Article article);
}
