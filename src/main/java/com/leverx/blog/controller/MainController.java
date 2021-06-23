package com.leverx.blog.controller;

import com.leverx.blog.model.dto.UserDto;
import com.leverx.blog.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@NoArgsConstructor
@RestController
public class MainController {

    private UserService userService;

    @GetMapping("/users/{userId}")
    public UserDto sayHello(@PathVariable("userId") Integer userId) {
        return userService.getUser(userId);
    }
}
