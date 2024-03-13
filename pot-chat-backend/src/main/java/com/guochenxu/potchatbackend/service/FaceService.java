package com.guochenxu.potchatbackend.service;

/**
 * 人脸服务
 */
public interface FaceService {
    /**
     * 人脸对比
     *
     * @param imgStr1 数据库中的人脸, 链接
     * @param imgStr2 base64字符串
     * @return 是否相同
     */
    boolean faceMatch(String imgStr1, String imgStr2);

    /**
     * 人脸检测
     */
    boolean faceDetect(String imgStr);
}