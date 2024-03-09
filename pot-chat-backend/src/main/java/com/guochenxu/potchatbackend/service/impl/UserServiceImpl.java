package com.guochenxu.potchatbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guochenxu.potchatbackend.mapper.UserMapper;
import com.guochenxu.potchatbackend.entity.User;
import com.guochenxu.potchatbackend.service.UserService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * (User)表服务实现类
 *
 * @author: guochenxu
 * @create: 2024-03-09 17:46:04
 * @version: 1.0
 */
@Service("userService")
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public boolean changeAvatar(long userId, String url) {
        return false;
    }
}

