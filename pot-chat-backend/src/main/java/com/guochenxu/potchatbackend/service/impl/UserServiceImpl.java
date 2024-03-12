package com.guochenxu.potchatbackend.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guochenxu.potchatbackend.dto.request.AddFaceReq;
import com.guochenxu.potchatbackend.dto.request.LoginReq;
import com.guochenxu.potchatbackend.dto.request.RegisterReq;
import com.guochenxu.potchatbackend.dto.response.LoginResp;
import com.guochenxu.potchatbackend.mapper.UserMapper;
import com.guochenxu.potchatbackend.entity.User;
import com.guochenxu.potchatbackend.service.FaceMatchService;
import com.guochenxu.potchatbackend.service.QiniuCloudService;
import com.guochenxu.potchatbackend.service.UserService;
import com.guochenxu.potchatbackend.service.VerifyCodeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

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
    @Resource
    private VerifyCodeService verifyCodeService;

    @Resource
    private QiniuCloudService qiniuCloudService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private FaceMatchService faceMatchService;

    @Override
    public boolean sendVerifyCode(String email) {
        return verifyCodeService.sendVerifyCode2Email(email);
    }

    @Override
    public boolean register(RegisterReq req) {
        if (!verifyCodeService.checkEmailVerifyCode(req.getEmail(), req.getVerifyCode())) {
            throw new RuntimeException("验证码错误");
        }
        User user = User.builder().email(req.getEmail()).nickname(req.getNickname())
                .password(req.getPassword()).avatar(req.getAvatar()).build();
        return userMapper.insert(user) > 0;
    }

    @Override
    public LoginResp login(LoginReq req) {
        User u = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getEmail, req.getEmail()));
        if (u == null || !StringUtils.equals(u.getPassword(), DigestUtil.bcrypt(req.getPassword()))) {
            return null;
        }

        StpUtil.login(u.getId());
        String token = StpUtil.getTokenValue();
        long expireTime = System.currentTimeMillis() + StpUtil.getTokenTimeout() * 1000L;
        return LoginResp.builder().token(token).avatar(u.getAvatar()).email(u.getEmail())
                .expireTime(String.valueOf(expireTime)).nickname(u.getNickname()).build();
    }

    @Override
    public LoginResp faceLogin(LoginReq req) {
        User u = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getEmail, req.getEmail()));
        if (u == null || StringUtils.isBlank(u.getFace())) {
            return null;
        }

        String base64 = null;
        try {
            base64 = Base64.getEncoder().encodeToString(req.getImage().getBytes());
        } catch (IOException e) {
            log.info("添加人脸时出错: ", e);
            throw new RuntimeException("人脸不存在");
        }

        if (!faceMatchService.faceMatch(u.getFace(), base64)) {
            return null;
        }
        StpUtil.login(u.getId());
        String token = StpUtil.getTokenValue();
        long expireTime = System.currentTimeMillis() + StpUtil.getTokenTimeout() * 1000L;
        return LoginResp.builder().token(token).avatar(u.getAvatar()).email(u.getEmail())
                .expireTime(String.valueOf(expireTime)).nickname(u.getNickname()).build();
    }

    @Override
    public boolean addFace(long userId, AddFaceReq req) {
        User u = userMapper.selectById(userId);
        if (u == null
                || !verifyCodeService.checkEmailVerifyCode(u.getEmail(), req.getVerifyCode())) {
            return false;
        }

        String base64;
        try {
            base64 = Base64.getEncoder().encodeToString(req.getImage().getBytes());
        } catch (IOException e) {
            log.info("添加人脸时出错: ", e);
            throw new RuntimeException("人脸不存在");
        }
        u.setFace(base64);
        userMapper.updateById(u);
        return true;
    }
}

