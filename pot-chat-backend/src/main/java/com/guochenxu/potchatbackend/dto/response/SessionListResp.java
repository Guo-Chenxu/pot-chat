package com.guochenxu.potchatbackend.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 会话列表返回值
 *
 * @author: 郭晨旭
 * @create: 2024-03-18 16:54
 * @version: 1.0
 */

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("会话列表")
public class SessionListResp implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("会话id")
    private String sessionId;

    @ApiModelProperty("会话描述")
    private String description;

    @ApiModelProperty("更新时间")
    private String updateTime;
}
