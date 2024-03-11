package com.guochenxu.potchatbackend.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.guochenxu.potchatbackend.utils.GsonUtils;
import com.guochenxu.potchatbackend.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 人脸对比
 */


public interface FaceMatchService {
    /**
     * 人脸对比
     *
     * @param imgStr1 数据库中的人脸, 链接
     * @param imgStr2 base64字符串
     * @return 是否相同
     */
    boolean faceMatch(String imgStr1, String imgStr2);
}