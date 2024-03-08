package com.guochenxu.potchatbackend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableMongoAuditing
@EnableAsync
@EnableScheduling
@Slf4j
@EnableSwagger2
@SpringBootApplication
public class PostChatBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostChatBackendApplication.class, args);
    }

}
