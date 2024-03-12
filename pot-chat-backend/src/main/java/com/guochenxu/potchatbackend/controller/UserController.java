package com.guochenxu.potchatbackend.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.server.HttpServerRequest;
import com.guochenxu.potchatbackend.constants.HttpCode;
import com.guochenxu.potchatbackend.dto.R;
import com.guochenxu.potchatbackend.dto.request.AddFaceReq;
import com.guochenxu.potchatbackend.dto.request.LoginReq;
import com.guochenxu.potchatbackend.dto.request.RegisterReq;
import com.guochenxu.potchatbackend.dto.response.LoginResp;
import com.guochenxu.potchatbackend.entity.User;
import com.guochenxu.potchatbackend.service.QiniuCloudService;
import com.guochenxu.potchatbackend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.Email;
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

    @PostMapping("/sendVerifyCode")
    @SaIgnore
    @ApiOperation("发送验证码")
    public R sendVerifyCode(@RequestParam("email") @Email String email) {
        return userService.sendVerifyCode(email)
                ? R.success()
                : R.error("验证码发送失败");
    }

    @PostMapping("/register")
    @SaIgnore
    @ApiOperation("注册")
    public R register(@RequestBody RegisterReq req) {
        return userService.register(req)
                ? R.success()
                : R.error();
    }

    @PostMapping("/login")
    @SaIgnore
    @ApiOperation("邮箱密码登录")
    public R<LoginResp> login(@RequestBody LoginReq req) {
        if (StringUtils.isBlank(req.getEmail()) || StringUtils.isBlank(req.getPassword())) {
            return R.error(HttpCode.PARAM_WRONG, "用户名和密码不能为空");
        }
        LoginResp resp = userService.login(req);
        return resp == null
                ? R.error(HttpCode.PARAM_WRONG, "用户名或密码错误")
                : R.success(resp);
    }

    @PostMapping("/faceLogin")
    @SaIgnore
    @ApiOperation("人脸登录")
    public R<LoginResp> faceLogin(@RequestBody LoginReq req) {
        if (StringUtils.isBlank(req.getEmail()) || req.getImage() == null || req.getImage().isEmpty()) {
            return R.error(HttpCode.PARAM_WRONG, "用户名和人脸信息不能为空");
        }

        LoginResp resp = userService.faceLogin(req);
        return resp == null
                ? R.error(HttpCode.PARAM_WRONG, "用户名或者人脸信息错误")
                : R.success(resp);
    }

    @PostMapping("/addFace")
    @SaCheckLogin
    @ApiOperation("添加人脸")
    public R addFace(@RequestBody AddFaceReq req) {
        return userService.addFace(StpUtil.getLoginIdAsLong(), req)
                ? R.success()
                : R.error("用户不存在或者验证码错误");
    }

    @PostMapping("/logout")
    @SaCheckLogin
    @ApiOperation("退出登录")
    public R logout() {
        StpUtil.logout();
        return R.success();
    }
}

