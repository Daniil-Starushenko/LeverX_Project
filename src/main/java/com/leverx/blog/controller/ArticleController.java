package com.leverx.blog.controller;

import com.leverx.blog.model.dto.ArticleDto;
import com.leverx.blog.service.ArticleService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ArticleController {

    private ModelMapper modelMapper;
    private ArticleService articleService;

    @PostMapping(value = "/articles")
    public ArticleDto createArticle(@RequestBody ArticleDto articleDto) {

    }


}
