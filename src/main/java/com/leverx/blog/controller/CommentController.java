package com.leverx.blog.controller;

import com.leverx.blog.exception.entity.InvalidateArgumentException;
import com.leverx.blog.model.dto.*;
import com.leverx.blog.model.entity.Article;
import com.leverx.blog.model.entity.ArticleStatus;
import com.leverx.blog.model.entity.Comment;
import com.leverx.blog.model.entity.User;
import com.leverx.blog.service.ArticleService;
import com.leverx.blog.service.CommentService;
import com.leverx.blog.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class CommentController {

    private ModelMapper modelMapper;
    private UserService userService;
    private ArticleService articleService;
    private CommentService commentService;

    @PostMapping("/article/{id}/comments")
    public void createComment(@PathVariable("id") Integer articleId,
                              @RequestBody CreateCommentDto commentDto,
                              Principal principal) {
        Article article = articleService.getArticle(articleId);
        if (article.getStatus() == ArticleStatus.DRAFT) {
            throw new InvalidateArgumentException("article is not public");
        }
        User currentUser = userService.findUser(principal.getName());
        commentService.saveComment(article, currentUser, commentDto.getMessage());
    }

    @GetMapping("/articles/{articleId}/comments/{commentId}")
    public CommentDto showComment(@PathVariable("articleId") Integer articleId,
                                  @PathVariable("commentId") Integer commentId) {
        return modelMapper.map(
                commentService.findByArticleIdAndCommentId(articleId, commentId),
                CommentDto.class
        );
    }

    @GetMapping("/articles/{articleId}/comments")
    public CommentsPageDto showCommentsInArticles(@PathVariable("articleId") Integer articleId,
                                                  @RequestParam Integer page,
                                                  @RequestParam Integer pageLimit) {
        Article article = articleService.getArticle(articleId);
        List<CommentDto> comments = commentService.findCommentsPage(article, page , pageLimit).stream()
                .map(comment -> modelMapper.map(comment, CommentDto.class))
                .collect(Collectors.toList());
        long totalRecords = commentService.countByArticle(article);
        return CommentsPageDto.builder()
                .page(page)
                .pageLimit(pageLimit)
                .totalRecords(totalRecords)
                .comments(comments)
                .build();
    }

    @DeleteMapping("/articles/{articleId}/comments/{commentId}")
    public void deleteComment(@PathVariable("articleId") Integer articleId,
                              @PathVariable("commentId") Integer commentId,
                              Principal principal) {
        User user = userService.findUser(principal.getName());
        Article article = articleService.getArticle(articleId);
        Comment comment = commentService.findCommentById(commentId);
        commentService.deleteComment(user, article, comment);
    }

}
