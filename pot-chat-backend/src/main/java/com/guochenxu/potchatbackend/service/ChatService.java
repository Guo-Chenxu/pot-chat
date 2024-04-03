package com.guochenxu.potchatbackend.service;

import com.guochenxu.potchatbackend.dto.request.ChatReq;
import com.guochenxu.potchatbackend.dto.response.CreateResp;
import com.guochenxu.potchatbackend.dto.response.SessionInfoResp;
import com.guochenxu.potchatbackend.dto.response.SessionListResp;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 聊天服务类
 *
 * @author: 郭晨旭
 * @create: 2024-03-17 14:22
 * @version: 1.0
 */
public interface ChatService {

    /**
     * 新增会话
     */
    CreateResp create(String userId, Integer promptId);

    /**
     * 删除会话
     */
    boolean delete(String userId, String sessionId);

    /**
     * 获取会话列表
     */
    List<SessionListResp> list(String userId);

    /**
     * 获取会话详情
     */
    SessionInfoResp info(String userId, String sessionId);

    /**
     * 对话
     */
    void chat(HttpServletResponse response, long userId, ChatReq req);
}
