package com.leverx.blog.security.mail.code;

import java.util.UUID;

public class CodeGenerator {

    public String generateCode() {
        return UUID.randomUUID().toString();
    }

}