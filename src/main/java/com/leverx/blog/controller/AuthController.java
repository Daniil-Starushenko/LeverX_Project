package com.leverx.blog.controller;

import com.leverx.blog.exception.auth.AuthException;
import com.leverx.blog.exception.entity.EntityNotFoundException;
import com.leverx.blog.model.dto.UserDto;
import com.leverx.blog.model.dto.UserSignInDto;
import com.leverx.blog.model.dto.UserSignUpDto;
import com.leverx.blog.model.entity.User;
import com.leverx.blog.model.entity.UserStatus;
import com.leverx.blog.security.code.ConfirmationToken;
import com.leverx.blog.security.jwt.JwtProvider;
import com.leverx.blog.security.mail.EmailBuilder;
import com.leverx.blog.security.mail.EmailSender;
import com.leverx.blog.security.code.ConfirmationTokenService;
import com.leverx.blog.security.code.CodeGenerator;
import com.leverx.blog.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final static String REGISTRATION_LINK = "http://localhost:8080/auth/confirmation?token=";

    private UserService userService;
    private ModelMapper modelMapper;
    private EmailBuilder emailBuilder;
    private EmailSender emailSender;
    private ConfirmationTokenService authorizationTokenService;
    private AuthenticationManager authenticationManager;
    private JwtProvider jwtProvider;

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
                emailBuilder.buildRegistryEmail(user.getFirstName(), getAuthorizationLink(user.getId())));
    }

    private String getAuthorizationLink(Integer userId) {
        CodeGenerator codeGenerator = new CodeGenerator();
        ConfirmationToken token = new ConfirmationToken();
        token.setTokenId(codeGenerator.generateCode());
        token.setUserId(userId);
        token.setTimeToLive(1L);

        authorizationTokenService.saveConfirmationToken(token);
        return REGISTRATION_LINK + token.getTokenId();
    }

    @GetMapping(value = "/confirmation")
    public void confirmRegistry(@RequestParam("token") String token) {
        if (authorizationTokenService.isDeleted(token)) {
            throw new AuthException(HttpStatus.NOT_FOUND,
                    "the authorization code is not active" + token);
        }
        ConfirmationToken authToken = authorizationTokenService.getTokenById(token);
        UserDto userToAuthorize = userService.getUser(authToken.getUserId());
        User user = modelMapper
                .map(userToAuthorize, User.class);
        userService.updateUserStatus(user, UserStatus.ACTIVATED);
    }

    @GetMapping(value = "/signin")
    public ResponseEntity login(@RequestBody UserSignInDto request) {
        String username = request.getEmail();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, request.getPassword()));
        User user = userService.findPresentUser(username)
                .orElseThrow((() -> new EntityNotFoundException("entity with username was not found: "
                        + username)));

        String token = jwtProvider.createToken(username, user.getUserStatus());
        Map<Object, Object> response = new HashMap<>();
        response.put("username", username);
        response.put("token", token);

        return ResponseEntity.ok(response);
    }

}
