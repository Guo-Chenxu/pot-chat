package com.guochenxu.potchatbackend;

import com.guochenxu.potchatbackend.service.FaceService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

/**
 * 人脸测试
 *
 * @author: 郭晨旭
 * @create: 2024-03-13 13:28
 * @version: 1.0
 */

@SpringBootTest
@Slf4j
public class FaceServiceTest {
    @Resource
    private FaceService faceService;

    @Test
    public void testFaceDetect() throws IOException {
        String path = "D:\\Tencent\\QQFiles\\2269409349\\FileRecv\\MobileFile\\mmexport1627705143339.jpg";
        File file = new File(path);
        FileInputStream in = new FileInputStream(path);
        byte[] data = new byte[(int) file.length()];
        in.read(data);
        String imgStr = Base64.getEncoder().encodeToString(data);
        boolean b = faceService.faceDetect(imgStr);
        log.info("检测结果: {}", b);
    }
}
