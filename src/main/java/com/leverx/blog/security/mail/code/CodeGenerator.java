package com.leverx.blog.security.mail.code;

import java.util.UUID;

public class CodeGenerator {

    private String code;

    public String generateCode() {
       code = UUID.randomUUID().toString();
       return code;
    }

    public String getCode() {
        return code;
    }

}