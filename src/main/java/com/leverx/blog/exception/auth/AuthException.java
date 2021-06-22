package com.leverx.blog.exception.auth;

import com.leverx.blog.exception.ApiException;
import com.leverx.blog.exception.errorcodes.AuthErrorCodes;

public class AuthException extends ApiException {

    public AuthException(AuthErrorCodes errorCode, String message) {
        super(errorCode, message);
    }

}
