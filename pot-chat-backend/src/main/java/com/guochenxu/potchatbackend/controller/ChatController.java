package com.guochenxu.potchatbackend.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.guochenxu.potchatbackend.dto.request.ChatReq;
import com.guochenxu.potchatbackend.service.ChatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * 聊天接口
 *
 * @author: 郭晨旭
 * @create: 2024-03-17 15:06
 * @version: 1.0
 */
@RestController
@RequestMapping("chat")
@Slf4j
@Api(tags = "聊天相关接口")
public class ChatController {

    @Resource
    private ChatService chatService;

    @PostMapping("/chat")
    @SaCheckLogin
    @ApiOperation("聊天接口")
    public void chat(HttpServletResponse response, @RequestBody ChatReq chatReq) {
        response.setContentType("text/event-stream;charset=UTF-8");
        chatService.chat(response, StpUtil.getLoginIdAsLong(), chatReq);
    }
}
