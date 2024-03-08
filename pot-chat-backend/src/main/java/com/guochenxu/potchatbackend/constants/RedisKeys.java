package com.guochenxu.potchatbackend.constants;

/**
 * redis中存储的键
 *
 * @author: 郭晨旭
 * @create: 2023-10-30 00:13
 * @version: 1.0
 */
public interface RedisKeys {

    String VERIFY_CODE_EMAIL = "POT_CHAT:VERIFY_CODE:EMAIL:%s"; // 邮箱验证码
}
