package com.leverx.blog.exception.auth;

import com.leverx.blog.exception.ApiException;
import org.springframework.http.HttpStatus;

public class AuthException extends ApiException {

    public AuthException(HttpStatus errorCode, String message) {
        super(errorCode, message);
    }

}
