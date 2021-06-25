package com.leverx.blog.model.entity;

import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;

public enum UserStatus implements GrantedAuthority {
    ACTIVATED,
    WAIT_ACTIVATING;

    @Override
    public String getAuthority() {
        return name();
    }

    public static String[] getAllAuthorities() {
        return Arrays.stream(UserStatus.values()).
                map(UserStatus::getAuthority)
                .toArray(String[]::new);
    }
}
