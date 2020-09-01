package cc.caker.boot.service.impl;

import cc.caker.boot.service.RedisServiceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author cakeralter
 * @date 2020/9/1
 */
@RequiredArgsConstructor
@Service
public class RedisServiceImpl extends RedisServiceAdapter {

    private final StringRedisTemplate redisTemplate;

    @Override
    public boolean put(String key, String value, long expire) {
        redisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
        return true;
    }

    @Override
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
