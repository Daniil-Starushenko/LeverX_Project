package com.leverx.blog.service;

import com.leverx.blog.model.entity.Article;
import com.leverx.blog.model.entity.Comment;
import com.leverx.blog.model.entity.User;
import org.springframework.data.domain.Page;

public interface CommentService {

    Comment saveComment(Comment comment);

    Comment saveComment(Article articleToComment, User commentingUser, String message);

    Comment findCommentById(Integer id);

    Comment findByArticleIdAndCommentId(Integer articleId, Integer commentId);

    Page<Comment> findCommentsPage(Article article, Integer page, Integer pageLimit);

    long countByArticle(Article article);

}
