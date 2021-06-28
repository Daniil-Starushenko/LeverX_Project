package com.leverx.blog.service.impl;

import com.leverx.blog.model.entity.Article;
import com.leverx.blog.model.entity.Comment;
import com.leverx.blog.model.entity.User;
import com.leverx.blog.repository.mysql.CommentRepository;
import com.leverx.blog.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    @Override
    public Comment saveComment(Comment comment) {
        return null;
    }

    @Override
    public Comment saveComment(Article articleToComment, User commentingUser, String message) {
        Comment newComment = new Comment();
        newComment.setArticle(articleToComment);
        newComment.setUser(commentingUser);
        newComment.setMessage(message);
        return commentRepository.save(newComment);
    }
}