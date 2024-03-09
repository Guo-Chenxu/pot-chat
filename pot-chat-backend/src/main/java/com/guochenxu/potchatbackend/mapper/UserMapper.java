package com.guochenxu.potchatbackend.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.guochenxu.potchatbackend.entity.User;

/**
 * (User)表数据库访问层
 *
 * @author: guochenxu
 * @create: 2024-03-09 17:46:03
 * @version: 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}

