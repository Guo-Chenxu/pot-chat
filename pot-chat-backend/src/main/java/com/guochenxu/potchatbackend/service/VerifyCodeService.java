package com.guochenxu.potchatbackend.service;

import javax.validation.constraints.NotNull;

/**
 * 验证码相关服务
 *
 * @author: 郭晨旭
 * @create: 2023-10-30 00:26
 * @version: 1.0
 */
public interface VerifyCodeService {
    /**
     * 给邮箱发送验证码
     */
    @NotNull
    boolean sendVerifyCode2Email(String email);

    /**
     * 通过邮箱检查验证码是否正确
     */
    @NotNull
    boolean checkEmailVerifyCode(String email, String verifyCode);
}
