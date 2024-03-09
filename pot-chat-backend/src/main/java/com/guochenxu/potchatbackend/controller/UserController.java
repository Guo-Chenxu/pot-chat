package com.guochenxu.potchatbackend.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.http.server.HttpServerRequest;
import com.guochenxu.potchatbackend.dto.R;
import com.guochenxu.potchatbackend.service.QiniuCloudService;
import com.guochenxu.potchatbackend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


/**
 * (User)表控制层
 *
 * @author: guochenxu
 * @create: 2024-03-09 17:46:03
 * @version: 1.0
 */
@RestController
@RequestMapping("user")
@Slf4j
@Api(tags = "用户相关接口")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private QiniuCloudService qiniuCloudService;

    @PostMapping("/uploadAvatar")
    @SaIgnore
    @ApiOperation("上传头像")
    public R uploadAvatar(@RequestParam("file") @NotNull MultipartFile file) {
        int idx = Optional.ofNullable(file.getOriginalFilename()).orElse(".").lastIndexOf('.');
        String imageName = "avatar/" + UUID.randomUUID().toString().replace("-", "")
                + file.getOriginalFilename().substring(idx);
        String url = qiniuCloudService.fileUpload(file, imageName);
        if (StringUtils.isBlank(url)) {
            return R.error("文件上传失败");
        }
        return R.success(url);
    }

    @PostMapping("/changeAvatar")
    @SaCheckLogin
    @ApiOperation("修改用户头像")
    public R changeAvatar(@RequestParam("file") @NotNull MultipartFile file) {
        int idx = Optional.ofNullable(file.getOriginalFilename()).orElse(".").lastIndexOf('.');
        String imageName = "avatar/" + UUID.randomUUID().toString().replace("-", "")
                + file.getOriginalFilename().substring(idx);
        String url = qiniuCloudService.fileUpload(file, imageName);
        return userService.changeAvatar(StpUtil.getLoginIdAsLong(), url)
                ? R.success(url) : R.error("修改失败");
    }
}

