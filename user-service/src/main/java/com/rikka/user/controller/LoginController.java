package com.rikka.user.controller;

import com.rikka.common.utils.JwtUtil;
import com.rikka.user.pojo.Users;
import com.rikka.user.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Slf4j
public class LoginController {

    @Autowired
    UsersService usersService;

    /**
     * 用户登录
     * @param name
     * @param pwd
     * @return
     */
    @PostMapping("/login")
    public String login(String name,String pwd){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(name,pwd);
        try {
            subject.login(usernamePasswordToken);
            Users users = usersService.selectUserByName(name);
            log.info("user: {}",users);
            Assert.notNull(users,"用户不存在");
            //    Assert.notNull(id,"id is null");
            //登陆成功，生成jwt token
            return JwtUtil.getToken(users.getId());
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return "error";
    }
}
