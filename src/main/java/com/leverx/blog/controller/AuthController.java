package com.leverx.blog.controller;

import com.leverx.blog.exception.auth.AuthException;
import com.leverx.blog.exception.errorcodes.AuthErrorCodes;
import com.leverx.blog.model.dto.UserSignUpDto;
import com.leverx.blog.model.entity.User;
import com.leverx.blog.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class AuthController {

    private UserService userService;

    private ModelMapper modelMapper;

    @PostMapping("/signup")
    public void signup(@RequestBody UserSignUpDto userSignUp) {
        User user = modelMapper.map(userSignUp, User.class);
        if (userService.existsUserByEmail(user.getEmail())) {
            throw new AuthException(AuthErrorCodes.EMAIL_ALREADY_TAKEN,
                    "the email is already taken: " + user.getEmail());
        }
        userService.saveUser(user);
    }

}
