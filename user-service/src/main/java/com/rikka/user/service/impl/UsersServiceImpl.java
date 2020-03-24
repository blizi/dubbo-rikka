package com.rikka.user.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.rikka.sso.service.RolesService;
import com.rikka.user.pojo.Users;
import com.rikka.user.mapper.UsersMapper;
import com.rikka.user.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.catalina.User;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author rikka
 * @since 2020-03-23
 */
@Component
@Service(version = "user-service")
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    @Reference(version = "sso-provider")
    RolesService rolesService;

    @Autowired
    UsersMapper usersMapper;

    @Override
    @HystrixCommand(fallbackMethod = "error")
    public String test() {
        return rolesService.test();
    }

    @Override
    public Users selectUserByName(String name) {
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        try {
            return usersMapper.selectByMap(map).stream().findFirst().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String error() {
        System.out.println("error");
        return "error";
    }
}
