package com.guochenxu.potchatbackend.service.impl;

import com.alibaba.fastjson2.JSON;
import com.guochenxu.potchatbackend.constants.RedisKeys;
import com.guochenxu.potchatbackend.dao.ChatSessionDao;
import com.guochenxu.potchatbackend.dto.request.ChatReq;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
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
