package com.guochenxu.potchatbackend.dao.impl;

import com.guochenxu.potchatbackend.constants.LLMBackground;
import com.guochenxu.potchatbackend.dao.ChatSessionDao;
import com.guochenxu.potchatbackend.entity.ChatSession;
import com.guochenxu.potchatbackend.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 聊天会话数据库接口实现类
 *
 * @author: 郭晨旭
 * @create: 2023-11-16 12:58
 * @version: 1.0
 */
@Component
public class ChatSessionDaoImpl implements ChatSessionDao {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public ChatSession saveSession(ChatSession chatSession) {
        // 删除背景对话
        if (!CollectionUtils.isEmpty(chatSession.getDialogues())) {
            if (LLMBackground.ROLE.equals(chatSession.getDialogues().get(0).getRole())) {
                chatSession.getDialogues().remove(0);
            }
        }

        chatSession.setUpdateTime(DateUtil.getNowTime());
        chatSession.setDeleted(0);
        return mongoTemplate.save(chatSession);
    }

    @Override
    public List<ChatSession> selectSessions(String userId) {
        Query q = Query.query(Criteria.where("userId").is(userId).and("deleted").is(0));
        return mongoTemplate.find(q, ChatSession.class);
    }

    @Override
    public ChatSession selectSessionByUserIdAndSessionId(String userId, String sessionId) {
        Query q = Query.query(Criteria.where("id").is(sessionId).and("userId").is(userId).and("deleted").is(0));
        return mongoTemplate.findOne(q, ChatSession.class);
    }

    @Override
    public long deleteSession(String userId, String sessionId) {
        Query q = Query.query(Criteria.where("id").is(sessionId).and("userId").is(userId).and("deleted").is(0));
        Update u = Update.update("deleted", 1);
        return mongoTemplate.updateMulti(q, u, ChatSession.class).getModifiedCount();
    }
}
