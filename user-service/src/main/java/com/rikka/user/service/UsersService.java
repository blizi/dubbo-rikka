package com.rikka.user.service;

import com.rikka.user.pojo.Users;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author rikka
 * @since 2020-03-23
 */
public interface UsersService extends IService<Users> {
    String test();
}
