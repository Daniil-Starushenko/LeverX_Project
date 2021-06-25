package com.leverx.blog.security.mail.code;

public interface AuthorizationTokenService {

    AuthorizationToken saveAuthorizationToken(AuthorizationToken token);

    boolean isDeleted(String tokenId);

    AuthorizationToken getTokenById(String tokenId);
}
