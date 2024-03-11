package com.guochenxu.potchatbackend.service;

/**
 * 邮件服务类
 */
public interface EmailService {


    /**
     * 发送邮件
     */
    void sendEmail(String to, String subject, String text);
}