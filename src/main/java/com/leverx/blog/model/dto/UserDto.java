package com.leverx.blog.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Data
public class UserDto {
    private Integer id;
    private String firstName;
    private String lastName;
    @JsonIgnore
    private String password;
    private String email;
    @JsonFormat(shape = STRING, pattern = "dd-MM-yyyy")
    private LocalDate registrationDate;
}
