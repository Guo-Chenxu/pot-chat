package com.guochenxu.potchatbackend.service.impl;

import com.guochenxu.potchatbackend.constants.EmailPattern;
import com.guochenxu.potchatbackend.constants.RedisKeys;
import com.guochenxu.potchatbackend.service.EmailService;
import com.guochenxu.potchatbackend.service.VerifyCodeService;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 验证码相关服务
 *
 * @author: 郭晨旭
 * @create: 2023-10-30 00:26
 * @version: 1.0
 */
@Service("verifyCodeService")
@Slf4j
public class VerifyCodeServiceImpl implements VerifyCodeService {
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 验证码 5 分钟过期
     */
    private static final Long EXPIRE_TIME = 5L;

    @Resource
    private EmailService emailService;

    /**
     * 生成验证码
     */
    @NotNull
    private String generateVerifyCode(String user) {
        Random random = new Random();
        long code = user.hashCode() ^ (random.nextInt(900000) + 100000);
        long nowTime = System.currentTimeMillis();
        code = (code ^ nowTime) % 1000000;
        code = code < 0 ? -code : code;
        return String.format("%06d", code);
    }

    @Override
    public boolean sendVerifyCode2Email(String email) {
        String verifyCode = generateVerifyCode(email);
        String key = String.format(RedisKeys.VERIFY_CODE_EMAIL, email);
        redisTemplate.opsForValue().set(key, verifyCode, EXPIRE_TIME, TimeUnit.MINUTES);
        log.info("{} 的验证码是: {}", email, verifyCode);

        emailService.sendEmail(email, EmailPattern.POT_CHAT_VERIFY_CODE_SUBJECT,
                String.format(EmailPattern.POT_CHAT_VERIFY_CODE_CONTENT, verifyCode, EXPIRE_TIME));
        return true;
    }

    @Override
    public boolean checkEmailVerifyCode(String email, String verifyCode) {
        String key = String.format(RedisKeys.VERIFY_CODE_EMAIL, email);
        String code = redisTemplate.opsForValue().get(key);
        if (!StringUtils.equals(code, verifyCode)) {
            return false;
        }
        redisTemplate.delete(key);
        return true;
    }
}
