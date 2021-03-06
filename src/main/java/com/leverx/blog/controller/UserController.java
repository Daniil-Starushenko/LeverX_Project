package com.leverx.blog.controller;

import com.leverx.blog.model.dto.UserDto;
import com.leverx.blog.security.code.CodeGenerator;
import com.leverx.blog.security.code.ConfirmationToken;
import com.leverx.blog.security.code.ConfirmationTokenService;
import com.leverx.blog.security.jwt.JwtProvider;
import com.leverx.blog.security.mail.EmailBuilder;
import com.leverx.blog.security.mail.EmailSender;
import com.leverx.blog.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private EmailBuilder emailBuilder;
    private EmailSender emailSender;
    private ConfirmationTokenService confirmationTokenService;
    private AuthenticationManager authenticationManager;
    private JwtProvider jwtProvider;

    @PostMapping(value = "/auth/forgot_password/{email}")
    public void forgotPassword(@PathVariable("email") String email) {
        UserDto user = userService.findPresentUserDto(email);
        sendEmailWithCode(user);
    }

    private void sendEmailWithCode(UserDto user) {
        emailSender.send(user.getEmail(),
                emailBuilder.generateEmailWithCode(getConfirmationCode(user)));
    }

    private String getConfirmationCode(UserDto userDto) {
        CodeGenerator codeGenerator = new CodeGenerator();
        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setTokenId(codeGenerator.generateCode());
        confirmationToken.setUserId(userDto.getId());
        confirmationToken.setTimeToLive(24L);
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return confirmationToken.getTokenId();
    }

    @PostMapping(value = "/auth/reset/{code}/{new_password}")
    public String resetPassword(@PathVariable("code") String confirmCode,
                                @PathVariable("new_password") String newPassword) {
        ConfirmationToken token = confirmationTokenService.getTokenById(confirmCode);
        //code recieved
        confirmationTokenService.deleteToken(token);
        return userService.changePasswordAndGenerateJwt(token.getUserId(), newPassword);
    }

    @GetMapping(value = "/auth/check_code/{code}")
    public ResponseEntity checkRelevance(@PathVariable("code") String code) {
        Map<String, Boolean> response = new HashMap<>();
        response.put("is code actual:", confirmationTokenService.isActive(code));
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/check/chech")
    public String check(Principal principal) {
        return principal.getName();
    }

}
