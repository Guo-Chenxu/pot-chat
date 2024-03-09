package com.guochenxu.potchatbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guochenxu.potchatbackend.entity.User;

/**
 * (User)表服务接口
 *
 * @author: guochenxu
 * @create: 2024-03-09 17:46:03
 * @version: 1.0
 */
public interface UserService extends IService<User> {

    /**
     * 修改用户头像
     */
    boolean changeAvatar(long userId, String url);
}

