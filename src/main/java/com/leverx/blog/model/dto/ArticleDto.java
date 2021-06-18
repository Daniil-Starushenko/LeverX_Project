package com.leverx.blog.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class ArticleDto {
    private Integer id;
    private String title;
    private String text;
    private StatusDto status;
    private UserDto user;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.mm.YYYY")
    private LocalDate changingDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.mm.YYYY")
    private LocalDate creationDate;
    private Set<CommentDto> comments;
    private Set<TagDto> tags;
}
