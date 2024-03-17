package com.guochenxu.potchatbackend.service.impl;

import com.guochenxu.potchatbackend.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 缓存服务实现类
 *
 * @author: 郭晨旭
 * @create: 2023-11-09 13:47
 * @version: 1.0
 */

@Service("cacheService")
@Slf4j
public class CacheServiceImpl implements CacheService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 用于设置随机过期时间
     */
    private static final Random RANDOM = new Random();

    @Override
    public void add(String key, String val) {
        stringRedisTemplate.opsForValue().set(key, val);
    }

    @Override
    public void addWithExpireTime(String key, String val, long expireTime, TimeUnit unit) {
        expireTime += RANDOM.nextInt((int) Math.min(Integer.MAX_VALUE, expireTime >> 1));
        stringRedisTemplate.opsForValue().set(key, val, expireTime, unit);
    }

    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean exist(String key) {
        return Boolean.TRUE.equals(stringRedisTemplate.hasKey(key));
    }

    @Override
    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    @Override
    public void renew(String key, long expireTime, TimeUnit unit) {
        String v = this.get(key);
        addWithExpireTime(key, v, expireTime, unit);
    }
}
