package com.leverx.blog.security.mail.code;

public interface AuthorizationTokenService {

    AuthorizationToken saveAuthorizationToken(AuthorizationToken token);

    boolean isDeleted(AuthorizationToken token);

    AuthorizationToken getTokenById(AuthorizationToken token);
}
