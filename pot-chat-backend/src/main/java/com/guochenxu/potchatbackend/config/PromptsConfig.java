package com.guochenxu.potchatbackend.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 提示词配置
 *
 * @author: 郭晨旭
 * @create: 2024-04-03 16:30
 * @version: 1.0
 */

@Configuration
@ConfigurationProperties(prefix = "llm")
@Data
public class PromptsConfig {

    private String[] prompts;
}
