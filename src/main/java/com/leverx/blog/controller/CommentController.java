package com.leverx.blog.controller;

import com.leverx.blog.exception.entity.InvalidateArgumentException;
import com.leverx.blog.model.dto.CreateCommentDto;
import com.leverx.blog.model.entity.Article;
import com.leverx.blog.model.entity.ArticleStatus;
import com.leverx.blog.model.entity.User;
import com.leverx.blog.service.ArticleService;
import com.leverx.blog.service.CommentService;
import com.leverx.blog.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

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




}
