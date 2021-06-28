package com.leverx.blog.controller;

import com.leverx.blog.service.ArticleService;
import com.leverx.blog.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CommentController {

    private ModelMapper modelMapper;
    private UserService userService;
    private ArticleService articleService;


}
