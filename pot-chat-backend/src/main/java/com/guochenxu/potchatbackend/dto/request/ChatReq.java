package com.guochenxu.potchatbackend.dto.request;

import com.guochenxu.potchatbackend.entity.spark.RoleContent;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 聊天请求
 *
 * @author: 郭晨旭
 * @create: 2024-03-17 15:50
 * @version: 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ApiModel("对话请求参数")
public class ChatReq implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("会话id")
    private String sessionId;

    @ApiModelProperty("历史记录")
    private List<RoleContent> history;

    @ApiModelProperty("用户输入")
    private String content;
}
