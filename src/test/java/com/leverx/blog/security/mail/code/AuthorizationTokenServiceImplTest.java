package com.leverx.blog.security.mail.code;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class AuthorizationTokenServiceImplTest {

    AuthorizationTokenServiceImpl authorizationTokenService = new AuthorizationTokenServiceImpl();

    private CodeGenerator codeGenerator = new CodeGenerator();
    private AuthorizationToken authorizationToken;

    @Before
    public void setUp() throws Exception {
        authorizationToken = new AuthorizationToken(codeGenerator.generateCode(), 1, 2L);
        System.out.println(authorizationToken.getTokenId());
    }

    @Test
    public void saveAuthorizationToken() {
        System.out.println(authorizationTokenService.saveAuthorizationToken(authorizationToken).getTokenId());
        System.out.println(authorizationTokenService.getTokenById(authorizationToken).getTokenId());
    }

    @Test
    public void isDeleted() {
    }

    @Test
    public void getTokenById() {
    }
}