package com.guochenxu.potchatbackend.constants;

/**
 * 邮件消息模板
 *
 * @author: 郭晨旭
 * @create: 2024-01-21 21:34
 * @version: 1.0
 */
public interface EmailPattern {
    String POT_CHAT_VERIFY_CODE_SUBJECT = "小锅聊天验证码";
    String POT_CHAT_VERIFY_CODE_CONTENT = "您的验证码是：%s，有效期为 %s 分钟。如非本人操作，请忽略本邮件。";
    String POT_CHAT_ADMIN_ADDRESS = "pot_chat2024@163.com";
}
