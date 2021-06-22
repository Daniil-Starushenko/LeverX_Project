package com.leverx.blog.model.dto;

import lombok.Data;

@Data
public class UserSignUpDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
