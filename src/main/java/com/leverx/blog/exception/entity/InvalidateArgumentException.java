package com.leverx.blog.exception.entity;

import com.leverx.blog.exception.ApiException;
import org.springframework.http.HttpStatus;

public class InvalidateArgumentException extends ApiException {
    public InvalidateArgumentException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
