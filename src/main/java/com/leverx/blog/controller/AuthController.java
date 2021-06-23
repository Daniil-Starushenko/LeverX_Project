package com.leverx.blog.controller;

import com.leverx.blog.exception.auth.AuthException;
import com.leverx.blog.model.dto.UserSignUpDto;
import com.leverx.blog.model.entity.User;
import com.leverx.blog.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void signup(@RequestBody UserSignUpDto userSignUp) {
        User user = modelMapper.map(userSignUp, User.class);
        if (userService.existsUserByEmail(user.getEmail())) {
            throw new AuthException(HttpStatus.BAD_REQUEST,
                    "the email is already taken: " + user.getEmail());
        }
        userService.saveUser(user);
    }

}
