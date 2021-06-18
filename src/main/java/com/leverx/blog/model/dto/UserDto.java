package com.leverx.blog.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class UserDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.mm.YYYY")
    private LocalDate registrationDate;
    private Set<CommentDto> comments;
    private Set<ArticleDto> articles;
}
