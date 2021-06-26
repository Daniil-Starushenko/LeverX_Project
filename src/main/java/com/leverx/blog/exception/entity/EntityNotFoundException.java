package com.leverx.blog.exception.entity;

import com.leverx.blog.exception.ApiException;
import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends ApiException {

    public EntityNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
