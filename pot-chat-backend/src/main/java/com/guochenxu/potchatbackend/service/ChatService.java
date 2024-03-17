package com.guochenxu.potchatbackend.service;

import com.guochenxu.potchatbackend.dto.request.ChatReq;

import javax.servlet.http.HttpServletResponse;

/**
 * 聊天服务类
 *
 * @author: 郭晨旭
 * @create: 2024-03-17 14:22
 * @version: 1.0
 */
public interface ChatService {

    /**
     * 对话
     */
    void chat(HttpServletResponse response, long userId, ChatReq req);
}
