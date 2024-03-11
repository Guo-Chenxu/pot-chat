package com.guochenxu.potchatbackend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PostChatBackendApplicationTests {

    @Value("${spring.mail.username}")
    private String from;

    @Test
    void contextLoads() {
    }

    @Test
    public void testGetFrom(){
        System.out.println(from);
    }
}
