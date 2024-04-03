package com.guochenxu.potchatbackend.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.guochenxu.potchatbackend.constants.LLMBackground;
import com.guochenxu.potchatbackend.dto.R;
import com.guochenxu.potchatbackend.dto.request.ChatReq;
import com.guochenxu.potchatbackend.dto.response.CreateResp;
import com.guochenxu.potchatbackend.dto.response.SessionInfoResp;
import com.guochenxu.potchatbackend.dto.response.SessionListResp;
import com.guochenxu.potchatbackend.service.ChatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/prompts")
    @SaCheckLogin
    @ApiOperation("查看所有提示词")
    private R<Map<Integer, String>> listPrompts() {
        Map<Integer, String> map = new HashMap<>();
        map.put(LLMBackground.PSYCHOLOGIST, "心理医生");
        map.put(LLMBackground.COMPUTER_TEACHER, "计算机老师");
        return R.success(map);
    }


    @PostMapping("/create")
    @SaCheckLogin
    @ApiOperation("创建聊天")
    public R<CreateResp> create(@RequestParam("promptId") Integer promptId) {
        return R.success(chatService.create(StpUtil.getLoginIdAsString(), promptId));
    }

    @DeleteMapping("/delete/{sessionId}")
    @SaCheckLogin
    @ApiOperation("删除会话")
    public R delete(@PathVariable("sessionId") String sessionId) {
        return chatService.delete(StpUtil.getLoginIdAsString(), sessionId)
                ? R.success()
                : R.error("删除失败");
    }

    @GetMapping("/list")
    @SaCheckLogin
    @ApiOperation("获取会话列表")
    public R<List<SessionListResp>> list() {
        return R.success(chatService.list(StpUtil.getLoginIdAsString()));
    }

    @GetMapping("/info/{sessionId}")
    @SaCheckLogin
    @ApiOperation("获取会话信息")
    public R<SessionInfoResp> info(@PathVariable("sessionId") String sessionId) {
        return R.success(chatService.info(StpUtil.getLoginIdAsString(), sessionId));
    }

    @PostMapping("/chat")
    @SaCheckLogin
    @ApiOperation("聊天接口")
    public void chat(HttpServletResponse response, @RequestBody ChatReq chatReq) {
        response.setContentType("text/event-stream;charset=UTF-8");
        chatService.chat(response, StpUtil.getLoginIdAsLong(), chatReq);
    }
}
