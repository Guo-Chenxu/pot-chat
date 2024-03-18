package com.guochenxu.potchatbackend.service.impl;

import com.alibaba.fastjson2.JSON;
import com.guochenxu.potchatbackend.constants.RedisKeys;
import com.guochenxu.potchatbackend.dao.ChatSessionDao;
import com.guochenxu.potchatbackend.dto.request.ChatReq;
import com.guochenxu.potchatbackend.dto.response.CreateResp;
import com.guochenxu.potchatbackend.dto.response.SessionInfoResp;
import com.guochenxu.potchatbackend.dto.response.SessionListResp;
import com.guochenxu.potchatbackend.entity.ChatSession;
import com.guochenxu.potchatbackend.entity.spark.RoleContent;
import com.guochenxu.potchatbackend.service.CacheService;
import com.guochenxu.potchatbackend.service.ChatService;
import com.guochenxu.potchatbackend.utils.SparkUtil;
import com.guochenxu.potchatbackend.websocket.SparkWebsocket;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 聊天服务实现类
 *
 * @author: 郭晨旭
 * @create: 2024-03-17 14:22
 * @version: 1.0
 */
@Service
@Slf4j
public class ChatServiceImpl implements ChatService {

    @Value("${spark.hostUrl}")
    private String hostUrl;

    @Value("${spark.apiSecret}")
    private String apiSecret;

    @Value("${spark.apiKey}")
    private String apiKey;

    @Value("${spark.appid}")
    private String appid;

    @Value("${spark.domain}")
    private String domain;

    @Resource
    private CacheService cacheService;

    @Resource
    private ChatSessionDao chatSessionDao;

    @Override
    public CreateResp create(String userId) {
        ChatSession chatSession = ChatSession.builder().userId(userId)
                .dialogues(new ArrayList<>()).build();
        chatSession = chatSessionDao.saveSession(chatSession);
        return CreateResp.builder().sessionId(chatSession.getId()).build();
    }

    @Override
    public boolean delete(String userId, String sessionId) {
        long res = chatSessionDao.deleteSession(userId, sessionId);
        return res > 0;
    }

    @Override
    public List<SessionListResp> list(String userId) {
        List<ChatSession> chatSessions = chatSessionDao.selectSessions(userId);
        List<SessionListResp> resp = new ArrayList<>();
        if (CollectionUtils.isEmpty(chatSessions)) {
            return resp;
        }

        chatSessions.forEach((e) -> {
            SessionListResp temp = SessionListResp.builder().sessionId(e.getId())
                    .description(CollectionUtils.isEmpty(e.getDialogues())
                            ? "新建聊天"
                            : e.getDialogues().get(0).getContent().substring(15))
                    .updateTime(e.getUpdateTime()).build();
            resp.add(temp);
        });
        return resp;
    }

    @Override
    public SessionInfoResp info(String userId, String sessionId) {
        ChatSession chatSession = chatSessionDao.selectSessionByUserIdAndSessionId(userId, sessionId);
        return SessionInfoResp.builder().sessionId(chatSession.getId())
                .history(chatSession.getDialogues())
                .build();
    }

    @Override
    public void chat(HttpServletResponse response, long userId, ChatReq req) {
        // 请求参数构造
        if (!cacheService.exist(String.format(RedisKeys.USER_SESSION, userId, req.getSessionId()))
                && chatSessionDao.selectSessionByUserIdAndSessionId(String.valueOf(userId), req.getSessionId()) == null) {
            throw new RuntimeException("请求参数错误, 用户不存在该会话");
        }
        List<RoleContent> dialogueList = req.getHistory();
        dialogueList.add(RoleContent.builder().content(req.getContent()).role("user").build());

        // 构建鉴权url
        String authUrl = null;
        try {
            authUrl = SparkUtil.getAuthUrl(hostUrl, apiKey, apiSecret);
        } catch (Exception e) {
            log.error("鉴权失败：", e);
        }
        OkHttpClient client = new OkHttpClient.Builder().build();
        String url = authUrl.toString().replace("http://", "ws://").replace("https://", "wss://");
        Request request = new Request.Builder().url(url).build();

        client.newWebSocket(request, new SparkWebsocket(userId, req.getSessionId(), dialogueList, response, appid, domain, cacheService));
        cacheService.delete(String.format(RedisKeys.USER_SESSION, userId, req.getSessionId()));
        while (!cacheService.exist(String.format(RedisKeys.USER_SESSION, userId, req.getSessionId()))) {
            // 空循环等待回复结束
        }

        String session = cacheService.get(String.format(RedisKeys.USER_SESSION, userId, req.getSessionId()));
        ChatSession chatSession = JSON.parseObject(session, ChatSession.class);
        chatSessionDao.saveSession(chatSession);
        log.info("chat返回");
    }
}
