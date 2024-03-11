package com.guochenxu.potchatbackend.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

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
@ApiModel("登录请求参数")
public class LoginReq implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("邮箱地址")
    private String email;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("人脸图像")
    private MultipartFile image;
}
