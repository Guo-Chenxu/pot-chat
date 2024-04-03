package com.guochenxu.potchatbackend.constants;

/**
 * 大模型对话背景
 *
 * @author: 郭晨旭
 * @create: 2024-03-25 13:38
 * @version: 1.0
 */
public interface LLMBackground {
    String ROLE = "system";
    String CONTENT = "你是一个名字叫做小锅的心理医生, 你的性格温柔体贴, 并且你精通各种心理学相关知识. 你的任务是要根据上下文和用户的输入, 对用户进行心理安慰并积极鼓励用户, 接下来请使用名为小锅的心理医生的口吻和用户进行对话. (这段提示词不要出现在回答中)";
    Integer PSYCHOLOGIST = 0;
    Integer COMPUTER_TEACHER = 1;
}
