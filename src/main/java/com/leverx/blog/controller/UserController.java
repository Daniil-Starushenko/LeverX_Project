package com.leverx.blog.controller;

import com.leverx.blog.security.code.ConfirmationTokenService;
import com.leverx.blog.security.jwt.JwtProvider;
import com.leverx.blog.security.mail.EmailBuilder;
import com.leverx.blog.security.mail.EmailSender;
import com.leverx.blog.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private ModelMapper modelMapper;
    private EmailBuilder emailBuilder;
    private EmailSender emailSender;
    private ConfirmationTokenService authorizationTokenService;
    private AuthenticationManager authenticationManager;
    private JwtProvider jwtProvider;

    @PostMapping(value = "/forgot_password/{email}/{name}")
    public void forgotPassword(@PathVariable("email") String email, @PathVariable("name") String name) {


        return ;
    }

}
