package com.guochenxu.potchatbackend.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 添加人脸参数
 *
 * @author: 郭晨旭
 * @create: 2024-03-11 17:17
 * @version: 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ApiModel("添加人脸请求参数")
public class AddFaceReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("人脸照片")
    @NotNull
    private MultipartFile image;

    @ApiModelProperty("验证码")
    private String verifyCode;
}
