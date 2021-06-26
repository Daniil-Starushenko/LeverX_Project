package com.leverx.blog.security.code;

public interface ConfirmationTokenService {

    ConfirmationToken saveConfirmationToken(ConfirmationToken token);

    boolean isDeleted(String tokenId);

    ConfirmationToken getTokenById(String tokenId);
}
