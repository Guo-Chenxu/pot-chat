package com.guochenxu.potchatbackend;

import cn.hutool.crypto.digest.DigestUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 密码哈希测试
 *
 * @author: 郭晨旭
 * @create: 2024-03-14 14:58
 * @version: 1.0
 */

@SpringBootTest
@Slf4j
public class DigestTest {

    @Test
    public void testBcrypt() {
        String p = "123";
        String bcrypt = DigestUtil.bcrypt(p);
        System.out.println(bcrypt);
        System.out.println(DigestUtil.bcryptCheck(p, bcrypt));
        bcrypt = DigestUtil.bcrypt(p);
        System.out.println(bcrypt);
    }

    @Test
    public void testMd5(){
        String p = "123";
        String md5 = DigestUtil.md5Hex(p);
        System.out.println(md5);
        System.out.println(DigestUtil.md5Hex(p));
        System.out.println(DigestUtil.md5Hex(p));
    }
}
