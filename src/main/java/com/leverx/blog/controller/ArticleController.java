package com.leverx.blog.controller;

import com.leverx.blog.exception.auth.NoCredentialsException;
import com.leverx.blog.model.dto.ArticleDto;
import com.leverx.blog.model.dto.ArticlePageDto;
import com.leverx.blog.model.dto.RequestArticleDto;
import com.leverx.blog.model.entity.*;
import com.leverx.blog.service.ArticleService;
import com.leverx.blog.service.TagService;
import com.leverx.blog.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class ArticleController {

    private ModelMapper modelMapper;
    private ArticleService articleService;
    private UserService userService;
    private TagService tagService;

    //TODO add method returning User, replace userDto
    @PostMapping(value = "/articles")
    public void createArticle(@RequestBody RequestArticleDto addArticle, Principal principal) {
        User currentUser = modelMapper
                .map(userService.findPresentUserDto(principal.getName()), User.class);
        currentUser.setUserStatus(UserStatus.ACTIVATED);
        Article newArticle = new Article();
        newArticle.setUser(currentUser);
        newArticle.setStatus(addArticle.getStatus());
        if (!addArticle.getTags().isEmpty()) {
            newArticle.setTags(getTags(addArticle.getTags()));
        }
        newArticle.setTitle(addArticle.getTitle());
        newArticle.setText(addArticle.getText());
        articleService.saveArticle(newArticle);
    }

    private Set<Tag> getTags(List<TagValue> tagValueList) {
        Set<Tag> tags = new HashSet<>();
        for (TagValue tagValue: tagValueList) {
            Tag tag = tagService.getByTagValue(tagValue);
            tags.add(tag);
        }
        return tags;
    }

    //TODO parameters
    @GetMapping("/articles")
    public ArticlePageDto getArticlesOnPage(@RequestParam Integer page,
                                            @RequestParam Integer pageLimit) {
        List<ArticleDto> articles = articleService.findArticlesOnPage(page, pageLimit).stream()
                .map(article -> modelMapper.map(article, ArticleDto.class))
                .collect(Collectors.toList());
        long totalRecords = articleService.articleCount();
        return ArticlePageDto.builder()
                .page(page)
                .pageLimit(pageLimit)
                .totalRecords(totalRecords)
                .articles(articles)
                .build();
    }

    @PutMapping("/articles/{id}")
    public void changeArticle(@PathVariable("id") Integer id,
                              @RequestBody RequestArticleDto articleDto,
                              Principal principal) {
        User currentUser = modelMapper
                .map(userService.findPresentUserDto(principal.getName()), User.class);
        Article article = articleService.getArticle(id);
        if (!currentUser.getId().equals(article.getUser().getId())) {
            throw new NoCredentialsException("no credentials");
        }
        article.setTitle(articleDto.getTitle());
        article.setText(articleDto.getText());
        article.setStatus(articleDto.getStatus());
        article.setChangingDate(LocalDate.now());
        article.setTags(getTags(articleDto.getTags()));
        articleService.updateArticle(article);
    }

    @GetMapping("/my")
    public ArticlePageDto getUsersArticles(@RequestParam Integer page,
                                           @RequestParam Integer pageLimit,
                                           Principal principal) {
        User currentUser = modelMapper
                .map(userService.findPresentUserDto(principal.getName()), User.class);
        List<ArticleDto> articles = articleService.findUsersArticlesOnPage(page, pageLimit, currentUser).stream()
                .map(article -> modelMapper.map(article, ArticleDto.class))
                .collect(Collectors.toList());
        long totalRecords = articleService.countUsersArticles(currentUser);
        return ArticlePageDto.builder()
                .page(page)
                .pageLimit(pageLimit)
                .totalRecords(totalRecords)
                .articles(articles)
                .build();
    }

    @DeleteMapping("/articles/{id}")
    public void deleteArticle(@PathVariable("id") Integer id, Principal principal) {
        User currentUser = modelMapper
                .map(userService.findPresentUserDto(principal.getName()), User.class);
        Article article = articleService.getArticle(id);
        if (!currentUser.getId().equals(article.getUser().getId())) {
            throw new NoCredentialsException("no credentials");
        }
        articleService.deleteArticle(id);
    }

    @GetMapping("/articles/tags")
    public ArticlePageDto getArticlesByTags(@RequestParam List<TagValue> tags,
                                            @RequestParam Integer page,
                                            @RequestParam Integer pageLimit) {
        List<ArticleDto> articles = articleService.findArticlesByTags(tags, page, pageLimit).stream()
                .map(article -> modelMapper.map(article, ArticleDto.class))
                .collect(Collectors.toList());
        return ArticlePageDto.builder()
                .page(page)
                .pageLimit(pageLimit)
                //.totalRecords(totalRecords)
                .articles(articles)
                .build();
    }

}
