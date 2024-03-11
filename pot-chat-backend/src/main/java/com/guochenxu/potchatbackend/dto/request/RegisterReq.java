package com.guochenxu.potchatbackend.dto.request;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录请求参数
 *
 * @author: 郭晨旭
 * @create: 2024-03-11 16:49
 * @version: 1.0
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ApiModel("注册请求参数")
public class RegisterReq implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("邮箱地址")
    private String email;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("头像")
    private String avatar;
}
