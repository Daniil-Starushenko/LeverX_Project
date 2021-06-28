package com.leverx.blog.exception.auth;

import com.leverx.blog.exception.ApiException;
import org.springframework.http.HttpStatus;

public class NoCredentialsException extends ApiException {
    public NoCredentialsException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
