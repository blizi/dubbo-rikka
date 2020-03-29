package com.rikka.user.controller;


import com.rikka.common.result.BaseResult;
import com.rikka.common.utils.JwtUtil;
import com.rikka.user.pojo.Users;
import com.rikka.user.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
    public BaseResult login(String name, String pwd){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(name,pwd);
        try {
            subject.login(usernamePasswordToken);
            Users users = usersService.selectUserByName(name);
            Assert.notNull(users,"用户不存在");
            log.info("登陆成功！ user: {}",users);
            //登陆成功，生成jwt token
            //todo 存储到redis服务器
            return BaseResult.success(JwtUtil.getToken(users.getId()));
        }catch ( UnknownAccountException uae ) {
            //username wasn't in the system, show them an error message?
            log.info("账号不存在");
        } catch ( IncorrectCredentialsException ice ) {
            //password didn't match, try again?
            log.info("密码错误");
        } catch (LockedAccountException lae ) {
            //account for that username is locked - can't login.  Show them a message?
            log.info("该账户已被锁");
        }catch (AuthenticationException e) {
            log.info("登录异常");
            e.printStackTrace();
        }
        return BaseResult.error("登录失败");
    }
}
