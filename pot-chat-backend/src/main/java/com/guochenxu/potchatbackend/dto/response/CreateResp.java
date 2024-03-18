package com.guochenxu.potchatbackend.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 创建会话返回值
 *
 * @author: 郭晨旭
 * @create: 2024-03-18 16:51
 * @version: 1.0
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ApiModel("创建会话返回值")
public class CreateResp implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("会话id")
    private String sessionId;
}
