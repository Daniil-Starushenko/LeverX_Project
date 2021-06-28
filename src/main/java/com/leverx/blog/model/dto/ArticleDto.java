package com.leverx.blog.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.leverx.blog.model.entity.ArticleStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Data
@EqualsAndHashCode
public class ArticleDto {
    private Integer id;
    private String title;
    private String text;
    private ArticleStatus status;
    private UserDto user;

    @JsonFormat(shape = STRING, pattern = "dd-MM-yyyy")
    private LocalDate changingDate;

    @JsonFormat(shape = STRING, pattern = "dd-MM-yyyy")
    private LocalDate creationDate;

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Set<CommentDto> comments;

    @EqualsAndHashCode.Exclude
    @JsonManagedReference
    private Set<TagDto> tags;
}
