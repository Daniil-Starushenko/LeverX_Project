package com.leverx.blog.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Data
public class UserDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    @JsonFormat(shape = STRING, pattern = "dd-MM-yyyy")
    private LocalDate registrationDate;
    private Set<CommentDto> comments;
    private Set<ArticleDto> articles;
}
