package com.leverx.blog.security.code;

import org.junit.Test;

public class CodeGeneratorTest {

    private CodeGenerator codeGenerator = new CodeGenerator();
    @Test
    public void generateCode() {
        System.out.println(codeGenerator.generateCode());
        System.out.println(codeGenerator.generateCode());
    }
}