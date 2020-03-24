package com.rikka.sso.controller;

import com.rikka.common.utils.JwtUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public String login(String name,String pwd){
//        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(name,pwd);
//        try {
//            subject.login(usernamePasswordToken);
//            //登陆成功，生成jwt token
//            return JwtUtil.getToken(12345L);
//        } catch (AuthenticationException e) {
//            e.printStackTrace();
//        }
       return "error";
    }

    @GetMapping("/test")
    public String test(){
        return "success";
    }
}
