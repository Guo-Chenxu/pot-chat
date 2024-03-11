package com.guochenxu.potchatbackend.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.guochenxu.potchatbackend.service.FaceMatchService;
import com.guochenxu.potchatbackend.utils.GsonUtils;
import com.guochenxu.potchatbackend.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 人脸对比
 */

@Slf4j
@Service
public class FaceMatchServiceImpl implements FaceMatchService {

    private static final double THRESHOLD = 80;

    @Value("${baidu.accessKey}")
    private String accessKey;

    @Value("${baidu.secretKey}")
    private String secretKey;

    /**
     * 获取API访问token
     * 该token有一定的有效期，需要自行管理，当失效时需重新获取.
     *
     * @param ak - 百度云官网获取的 API Key
     * @param sk - 百度云官网获取的 Securet Key
     * @return assess_token 示例：
     * "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567"
     */
    private String getAuth(String ak, String sk) {
        // 获取token地址
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + ak
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + sk;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }

            /**
             * 返回结果示例
             */
            log.info("result: {}", result);
            JSONObject jsonObject = JSONObject.parseObject(result);
            return jsonObject.getString("access_token");
        } catch (Exception e) {
            log.error("获取token失败！, ", e);
        }
        return null;
    }

    /**
     * 人脸对比
     *
     * @param imgStr1 数据库中的人脸, 链接
     * @param imgStr2 base64字符串
     * @return 是否相同
     */
    @Override
    public boolean faceMatch(String imgStr1, String imgStr2) {
        if (imgStr1 == null || imgStr2 == null) {
            return false;
        }

        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/match";
        try {
            ArrayList<HashMap<String, String>> map = new ArrayList<>();
            HashMap<String, String> map1 = new HashMap<>();
            HashMap<String, String> map2 = new HashMap<>();
            map1.put("image", imgStr1);
            map1.put("image_type", "BASE64");
            map1.put("face_type", "LIVE");
            map1.put("quality_control", "LOW");
            map1.put("liveness_control", "NONE");
            map2.put("image", imgStr2);
            map2.put("image_type", "BASE64");
            map2.put("face_type", "LIVE");
            map2.put("quality_control", "LOW");
            map2.put("liveness_control", "NONE");
            map.add(map1);
            map.add(map2);

            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = this.getAuth(accessKey, secretKey);

            String result = HttpUtil.post(url, accessToken, "application/json", param);

            // 获取人脸比对的结果
            String result1 = JSON.toJSONString(JSONObject.parseObject(result).get("result"));
            double score = Double.parseDouble(String.valueOf(JSONObject.parseObject(result1).get("score")));

            return score >= THRESHOLD;
        } catch (Exception e) {
            log.error("出现错误: ", e);
        }
        return false;
    }
}