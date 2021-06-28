package com.leverx.blog.service.impl;

import com.leverx.blog.exception.entity.EntityNotFoundException;
import com.leverx.blog.exception.entity.InvalidateArgumentException;
import com.leverx.blog.model.entity.Article;
import com.leverx.blog.model.entity.Comment;
import com.leverx.blog.model.entity.User;
import com.leverx.blog.repository.mysql.ArticleRepository;
import com.leverx.blog.repository.mysql.CommentRepository;
import com.leverx.blog.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private ArticleRepository articleRepository;

    @Override
    @Transactional(readOnly = true)
    public Comment saveComment(Comment comment) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Comment findByArticleIdAndCommentId(Integer articleId, Integer commentId) {
        log.info("try to find comment in article");
        return commentRepository.findByArticleIdAndId(articleId, commentId)
                .orElseThrow(() -> new EntityNotFoundException("there is no such comment"));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Comment> findCommentsPage(Article article, Integer pageNumber, Integer pageLimit) {
        if (pageNumber <= 0 || pageLimit <= 0 || pageLimit > 10) {
            throw new InvalidateArgumentException("Page starts from 1. Provided: "
                    + pageNumber + ". Page limit minimal value is 1. Provided: "
                    + pageLimit);
        }
        PageRequest pageRequest = PageRequest.of(pageNumber - 1,
                pageLimit);

        return commentRepository.findAllByArticle(article, pageRequest);
    }

    @Override
    public long countByArticle(Article article) {
        return commentRepository.countByArticle(article);
    }

    @Override
    public void deleteComment(User user, Article article, Comment comment) {
        if (!comment.getArticle().getId().equals(article.getId())) {
            throw new InvalidateArgumentException("there is no such comment in article");
        }
        if (user.getId().equals(article.getUser().getId()) ||
        user.getId().equals(comment.getUser().getId())) {
            article.getComments().remove(comment);
            articleRepository.save(article);
            commentRepository.deleteById(comment.getId());
        }
    }

    @Override
    public Comment saveComment(Article articleToComment, User commentingUser, String message) {
        Comment newComment = new Comment();
        newComment.setArticle(articleToComment);
        newComment.setUser(commentingUser);
        newComment.setMessage(message);
        return commentRepository.save(newComment);
    }

    @Override
    @Transactional(readOnly = true)
    public Comment findCommentById(Integer id) {
        log.info("try to find comment with id {}", id);
        return commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("comment with id not found: " + id));
    }

}
