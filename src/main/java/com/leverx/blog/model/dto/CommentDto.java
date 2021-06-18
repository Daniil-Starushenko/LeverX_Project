package com.leverx.blog.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
public class CommentDto {
    private Integer id;
    private String message;
    private UserDto user;
    private ArticleDto article;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm-yyyy")
    private LocalDate created;
}
