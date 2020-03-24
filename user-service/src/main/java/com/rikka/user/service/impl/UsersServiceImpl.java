package com.rikka.user.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.rikka.sso.service.RolesService;
import com.rikka.user.pojo.Users;
import com.rikka.user.mapper.UsersMapper;
import com.rikka.user.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author rikka
 * @since 2020-03-23
 */
@Component
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    @Reference(version = "sso-provider")
    RolesService rolesService;

    @Override
    @HystrixCommand(fallbackMethod = "error")
    public String test() {
        return rolesService.test();
    }

    public String error() {
        System.out.println("error");
        return "error";
    }
}
