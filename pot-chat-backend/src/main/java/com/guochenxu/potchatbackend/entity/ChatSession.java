package com.guochenxu.potchatbackend.entity;

import com.guochenxu.potchatbackend.entity.spark.RoleContent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * 聊天会话
 *
 * @author: 郭晨旭
 * @create: 2023-11-14 00:12
 * @version: 1.0
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class ChatSession implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    /**
     * 用户id
     */
    @Indexed(name = "idx_userId")
    private String userId;
    /**
     * 对话id
     */
    private List<RoleContent> dialogues;
    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 软删除
     */
    @Indexed(name = "idx_deleted")
    private Integer deleted;
}
