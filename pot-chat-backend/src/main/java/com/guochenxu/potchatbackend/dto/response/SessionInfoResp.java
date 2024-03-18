package com.guochenxu.potchatbackend.dto.response;

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
 * 会话详情返回值
 *
 * @author: 郭晨旭
 * @create: 2024-03-18 16:53
 * @version: 1.0
 */
@ApiModel("会话详情")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SessionInfoResp implements Serializable {

    private static final long serialVersionUID = 847736046627199L;

    @ApiModelProperty("会话id")
    private String sessionId;

    @ApiModelProperty("对话记录")
    private List<RoleContent> history;
}
