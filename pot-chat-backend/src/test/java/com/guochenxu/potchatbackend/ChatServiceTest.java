package com.guochenxu.potchatbackend;

import com.guochenxu.potchatbackend.dao.ChatSessionDao;
import com.guochenxu.potchatbackend.entity.ChatSession;
import com.guochenxu.potchatbackend.entity.spark.RoleContent;
import com.guochenxu.potchatbackend.service.ChatService;
import com.guochenxu.potchatbackend.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 聊天服务测试
 *
 * @author: 郭晨旭
 * @create: 2024-03-17 14:35
 * @version: 1.0
 */
@SpringBootTest
@Slf4j
public class ChatServiceTest {

    @Resource
    private ChatService chatService;

    @Resource
    private ChatSessionDao chatSessionDao;

    @Test
    public void testInsert() {
        List<RoleContent> contents = new ArrayList<>();
        contents.add(RoleContent.builder().role("123").content("321").build());
        contents.add(RoleContent.builder().role("123").content("321").build());
        contents.add(RoleContent.builder().role("123").content("321").build());
        contents.add(RoleContent.builder().role("123").content("321").build());
        contents.add(RoleContent.builder().role("123").content("321").build());
        ChatSession session = ChatSession.builder().id("65f6a67b0ba3595ee2cd26f3").userId("123")
                .dialogues(contents).build();
        chatSessionDao.saveSession(session);
    }
}
