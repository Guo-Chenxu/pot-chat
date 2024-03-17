package com.guochenxu.potchatbackend.dao;


import com.guochenxu.potchatbackend.entity.ChatSession;

import java.util.List;

/**
 * 聊天会话数据库接口
 *
 * @author: 郭晨旭
 * @create: 2023-11-16 12:58
 * @version: 1.0
 */
public interface ChatSessionDao {
    /**
     * 保存一次会话
     */
    ChatSession saveSession(ChatSession chatSession);

    /**
     * 根据用户id查询会话
     */
    List<ChatSession> selectSessions(String userId);

    /**
     * 根据用户id和会话id查询
     */
    ChatSession selectSessionByUserIdAndSessionId(String userId, String sessionId);

    /**
     * 删除用户会话， 软删除
     */
    long deleteSession(String userId, String sessionId);
}
