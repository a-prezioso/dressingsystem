package com.sviluppatoredisuccesso.dressing.controller;

import com.sviluppatoredisuccesso.dressing.entity.Role;
import com.sviluppatoredisuccesso.dressing.entity.UserEntity;
import com.sviluppatoredisuccesso.dressing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/nontest")
    public String nontest() {
        return "nontest";
    }



}
