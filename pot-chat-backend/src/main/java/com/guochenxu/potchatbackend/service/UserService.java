package com.guochenxu.potchatbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guochenxu.potchatbackend.dto.request.AddFaceReq;
import com.guochenxu.potchatbackend.dto.request.LoginReq;
import com.guochenxu.potchatbackend.dto.request.RegisterReq;
import com.guochenxu.potchatbackend.dto.response.LoginResp;
import com.guochenxu.potchatbackend.entity.User;
import org.springframework.web.multipart.MultipartFile;

/**
 * (User)表服务接口
 *
 * @author: guochenxu
 * @create: 2024-03-09 17:46:03
 * @version: 1.0
 */
public interface UserService extends IService<User> {

    /**
     * 发送验证码
     */
    boolean sendVerifyCode(String email);

    /**
     * 注册
     */
    boolean register(RegisterReq req);

    /**
     * 邮箱密码登录
     */
    LoginResp login(String email, String password);

    /**
     * 人脸登录
     */
    LoginResp faceLogin(String email, MultipartFile image);

    /**
     * 添加人脸
     */
    boolean addFace(long userId, MultipartFile image, String verifyCode);
}

