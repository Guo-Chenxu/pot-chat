package com.guochenxu.potchatbackend.entity;


import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;


/**
 * (User)表实体类
 *
 * @author: guochenxu
 * @create: 2024-03-09 17:46:03
 * @version: 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键, 唯一标识
     */
    private Long id;
    /**
     * 手机号
     */
    private String phoneNumber;
    /**
     * 邮箱地址
     */
    private String email;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 密码
     */
    private String password;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 创建时间
     */
    private Date createdAt;
}

