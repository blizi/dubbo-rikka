package com.rikka.user.controller;


import com.rikka.user.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author rikka
 * @since 2020-03-23
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersService service;

    @GetMapping
    public String test() {
        return service.test();
    }
}

