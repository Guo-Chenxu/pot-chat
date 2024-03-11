package com.guochenxu.potchatbackend.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 登录返回结果
 *
 * @author: 郭晨旭
 * @create: 2024-03-11 16:56
 * @version: 1.0
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ApiModel("登录返回结果")
public class LoginResp implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("token")
    private String token;

    @ApiModelProperty("token过期时间, 毫秒级时间戳")
    private String expireTime;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("头像")
    private String avatar;
}
