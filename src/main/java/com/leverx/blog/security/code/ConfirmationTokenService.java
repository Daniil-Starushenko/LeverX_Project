package com.leverx.blog.security.code;

public interface ConfirmationTokenService {

    ConfirmationToken saveConfirmationToken(ConfirmationToken token);

    boolean isDeleted(String tokenId);

    boolean isActive(String tokenId);

    ConfirmationToken getTokenById(String tokenId);

    void deleteTokenByTokenId(String tokenId);

    void deleteToken(ConfirmationToken tokenToDelete);

}
