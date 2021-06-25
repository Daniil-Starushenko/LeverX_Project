package com.leverx.blog.controller;

import com.leverx.blog.exception.auth.AuthException;
import com.leverx.blog.model.dto.UserSignUpDto;
import com.leverx.blog.model.entity.User;
import com.leverx.blog.security.mail.EmailBuilder;
import com.leverx.blog.security.mail.EmailSender;
import com.leverx.blog.security.mail.code.AuthorizationToken;
import com.leverx.blog.security.mail.code.AuthorizationTokenService;
import com.leverx.blog.security.mail.code.AuthorizationTokenServiceImpl;
import com.leverx.blog.security.mail.code.CodeGenerator;
import com.leverx.blog.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private UserService userService;

    private ModelMapper modelMapper;

    private EmailBuilder emailBuilder;

    private EmailSender emailSender;

    private AuthorizationTokenService authorizationTokenService;

    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void signup(@RequestBody UserSignUpDto userSignUp) {
        User user = modelMapper.map(userSignUp, User.class);
        if (userService.existsUserByEmail(user.getEmail())) {
            throw new AuthException(HttpStatus.BAD_REQUEST,
                    "the email is already taken: " + user.getEmail());
        }
        sendConfirmationMail(userService.saveUser(user));
    }

    private void sendConfirmationMail(User user) {
        emailSender.send(user.getEmail(),
                emailBuilder.buildEmail(user.getFirstName(), getAuthorizationLink(user.getId())));
    }

    private String getAuthorizationLink(Integer userId) {
        CodeGenerator codeGenerator = new CodeGenerator();
        AuthorizationToken token = new AuthorizationToken();
        token.setTokenId(codeGenerator.generateCode());
        token.setUserId(userId);
        token.setTimeToLive(50L);

        authorizationTokenService.saveAuthorizationToken(token);
        return "http://localhost:8080/auth/confirmation?token=" + token.getTokenId();
    }

    @GetMapping(value = "/confirmation")
    public String confirmRegistry(@RequestParam("token") String token) {
        AuthorizationToken authorizationToken = authorizationTokenService.getTokenById(token);

        return authorizationToken.getTokenId();
    }

}
