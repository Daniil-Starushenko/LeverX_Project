package com.leverx.blog.controller;

import com.leverx.blog.model.dto.ArticleDto;
import com.leverx.blog.model.dto.RequestArticleDto;
import com.leverx.blog.model.entity.*;
import com.leverx.blog.service.ArticleService;
import com.leverx.blog.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
public class ArticleController {

    private ModelMapper modelMapper;
    private ArticleService articleService;
    private UserService userService;

    @PostMapping(value = "/articles")
    public void createArticle(@RequestBody RequestArticleDto addArticle, Principal principal) {
        User currentUser = modelMapper
                .map(userService.findPresentUserDto(principal.getName()), User.class);
        currentUser.setUserStatus(UserStatus.ACTIVATED);
        Article newArticle = new Article();
        newArticle.setUser(currentUser);
        newArticle.setStatus(addArticle.getStatus());
        if (!addArticle.getTags().isEmpty()) {
            Set<Tag> tags = new HashSet<>();
            for (TagValue tagValue: addArticle.getTags()) {
                Tag tag = new Tag();
                tag.setTagValue(tagValue);
                tags.add(tag);
            }
            newArticle.setTags(tags);
        }
        newArticle.setTitle(addArticle.getTitle());
        newArticle.setText(addArticle.getText());
        articleService.saveArticle(newArticle);
    }

    private Set<Tag> saveTags(List<TagValue> tagValueList) {


        return null;
    }

}
