package com.rikka.sso.controller;


import com.rikka.sso.pojo.Roles;
import com.rikka.sso.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author rikka
 * @since 2020-03-23
 */
@RestController
@RequestMapping("/roles")
public class RolesController {
    @Autowired
    RolesService rolesService;

    @GetMapping()
    public String test() {
        return "aaaa";
    }
}

