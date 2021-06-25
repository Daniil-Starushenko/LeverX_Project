package com.leverx.blog.model.entity;

import org.springframework.security.core.GrantedAuthority;

public enum UserStatus implements GrantedAuthority {
    ACTIVATED,
    WAIT_ACTIVATING;

    @Override
    public String getAuthority() {
        return name();
    }
}
