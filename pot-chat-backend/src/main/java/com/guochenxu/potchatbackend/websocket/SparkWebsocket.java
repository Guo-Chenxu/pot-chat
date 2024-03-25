package com.guochenxu.potchatbackend.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.guochenxu.potchatbackend.constants.LLMBackground;
import com.guochenxu.potchatbackend.constants.RedisKeys;
import com.guochenxu.potchatbackend.entity.ChatSession;
import com.guochenxu.potchatbackend.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import com.guochenxu.potchatbackend.entity.spark.*;

/**
 * 星火websocket通信
 *
 * @author: 郭晨旭
 * @create: 2024-03-17 14:16
 * @version: 1.0
 */
@Slf4j
public class SparkWebsocket extends WebSocketListener {
    // 地址与鉴权信息  https://spark-api.xf-yun.com/v1.1/chat   1.5地址  domain参数为general
    // 地址与鉴权信息  https://spark-api.xf-yun.com/v2.1/chat   2.0地址  domain参数为generalv2
    private String appid;

    private String domain;

    private List<RoleContent> historyList; // 对话历史存储集合

    private Boolean wsCloseFlag; // 控制ws连接是否关闭

    private PrintWriter writer; // http返回流，用于sse返回

    private CacheService cacheService;

    private long userId; // 用户id

    private String sessionId; // 会话id

    private StringBuilder totalAnswer; // 总回答

    // 构造函数
    public SparkWebsocket(long userId, String sessionId, List<RoleContent> historyList, HttpServletResponse response,
                          String appid, String domain, CacheService cacheService) {
        this.userId = userId;
        this.sessionId = sessionId;
        this.wsCloseFlag = false;
        this.historyList = historyList;
        this.appid = appid;
        this.domain = domain;
        this.cacheService = cacheService;
        this.totalAnswer = new StringBuilder();
        try {
            this.writer = response.getWriter();
        } catch (IOException e) {
            log.error("获取http返回流失败: ", e);
        }
    }

    // 线程来发送音频与参数
    class MyThread extends Thread {
        private WebSocket webSocket;

        public MyThread(WebSocket webSocket) {
            this.webSocket = webSocket;
        }

        public void run() {
            try {
                JSONObject requestJson = new JSONObject();

                JSONObject header = new JSONObject();  // header参数
                header.put("app_id", appid);
                header.put("uid", UUID.randomUUID().toString().substring(0, 10));

                JSONObject parameter = new JSONObject(); // parameter参数
                JSONObject chat = new JSONObject();
                chat.put("domain", domain);
                chat.put("temperature", 0.5);
                chat.put("max_tokens", 4096);
                parameter.put("chat", chat);

                JSONObject payload = new JSONObject(); // payload参数
                JSONObject message = new JSONObject();
                JSONArray text = new JSONArray();

                // 添加角色背景
//                text.add(JSON.toJSON(RoleContent.builder().role(LLMBackground.ROLE)
//                        .content(LLMBackground.CONTENT).build()));

                historyList.forEach((e) -> text.add(JSON.toJSON(
                        RoleContent.builder()
                                .role(e.getRole())
                                .content("user".equals(e.getRole())
                                        ? LLMBackground.CONTENT + "\n" + e.getContent()
                                        : e.getContent())
                                .build())
                ));

                message.put("text", text);
                payload.put("message", message);

                requestJson.put("header", header);
                requestJson.put("parameter", parameter);
                requestJson.put("payload", payload);

                log.info("发送消息: {}", requestJson);
                writer.write("data: [start]\n\n");
                writer.flush();
                webSocket.send(requestJson.toString());

                // 等待服务端返回完毕后关闭
                while (!wsCloseFlag) {
                    Thread.sleep(200);
                }
                webSocket.close(1000, "");
            } catch (Exception e) {
                log.error("MyThread异常, ", e);
            }
        }
    }

    @Override
    public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
        super.onOpen(webSocket, response);
        MyThread myThread = new MyThread(webSocket);
        myThread.start();
    }

    @Override
    public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
        JsonParse myJsonParse = JSON.parseObject(text, JsonParse.class);
        if (myJsonParse.getHeader().getCode() != 0) {
            log.error("发生错误，错误码为：{}", myJsonParse.getHeader().getCode());
            log.error("本次请求的sid为：{}", myJsonParse.getHeader().getSid());
            webSocket.close(1000, "");
        }

        List<Text> textList = myJsonParse.getPayload().getChoices().getText();
        for (Text temp : textList) {
            totalAnswer.append(temp.getContent());
            writer.write("data: " + temp.getContent() + "\n\n");
            writer.flush();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if (myJsonParse.getHeader().getStatus() == 2) {
            writer.write("data: [done]\n\n");
            writer.flush();
            log.info("回复结束, 总答案为: {}", totalAnswer.toString());
            // 结束后在缓存中放入该对话
            historyList.add(RoleContent.builder().role("assistant").content(totalAnswer.toString()).build());
            ChatSession chatSession = ChatSession.builder().id(sessionId).userId(String.valueOf(userId))
                    .dialogues(historyList).build();
            cacheService.add(String.format(RedisKeys.USER_SESSION, userId, sessionId), JSON.toJSONString(chatSession));
            wsCloseFlag = true;
        }
    }

    @Override
    public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, Response response) {
        super.onFailure(webSocket, t, response);
        try {
            if (null != response) {
                int code = response.code();
                log.error("onFailure code: {}", code);
                log.error("onFailure body: {}", response.body().string());
                if (101 != code) {
                    log.error("connection failed");
                }
            }
        } catch (IOException e) {
            log.error("ws onFailure error: ", e);
        }
    }
}
