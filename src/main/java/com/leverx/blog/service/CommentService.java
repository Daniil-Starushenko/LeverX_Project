package com.leverx.blog.service;

import com.leverx.blog.model.entity.Article;
import com.leverx.blog.model.entity.Comment;
import com.leverx.blog.model.entity.User;

import java.util.Optional;

public interface CommentService {

    Comment saveComment(Comment comment);

    Comment saveComment(Article articleToComment, User commentingUser, String message);

    Comment findCommentById(Integer id);

    Comment findByArticleIdAndCommentId(Integer articleId, Integer commentId);
}
