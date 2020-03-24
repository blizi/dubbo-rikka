package com.rikka.sso.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.rikka.sso.pojo.Roles;
import com.rikka.sso.mapper.RolesMapper;
import com.rikka.sso.service.RolesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;
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
public class RolesServiceImpl extends ServiceImpl<RolesMapper, Roles> implements RolesService {

    @Value("${dubbo.application.name}")
    private String name;

    @Override
    @HystrixCommand
    public String test() {
        return name;
    }
}
