package com.leverx.blog.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leverx.blog.model.entity.ArticleStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Data
public class ArticleDto {
    private Integer id;
    private String title;
    private String text;
    private ArticleStatus status;
    private UserDto user;
    @JsonFormat(shape = STRING, pattern = "dd-MM-yyyy")
    private LocalDate changingDate;
    @JsonFormat(shape = STRING, pattern = "dd-mm-yyyy")
    private LocalDate creationDate;
    private Set<CommentDto> comments;
    private Set<TagDto> tags;
}
