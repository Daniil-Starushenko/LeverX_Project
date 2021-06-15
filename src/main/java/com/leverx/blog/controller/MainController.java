package com.leverx.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/say/hello")
    public String sayHello() {
        return "hello";
    }
}
