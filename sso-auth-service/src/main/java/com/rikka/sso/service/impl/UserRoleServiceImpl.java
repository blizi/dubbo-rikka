package com.rikka.sso.service.impl;

import com.rikka.sso.pojo.UserRole;
import com.rikka.sso.mapper.UserRoleMapper;
import com.rikka.sso.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author rikka
 * @since 2020-03-23
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
