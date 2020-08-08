package cc.caker.springboot.module.global.service.impl;

import cc.caker.common.service.RedisService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static cc.caker.springboot.constant.RedisConstant.DEFAULT_KEY_EXPIRE;

/**
 * @author cakeralter
 * @since 2020/8/6
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class RedisServiceImpl implements RedisService {

    private final StringRedisTemplate template;
    private final ObjectMapper mapper;

    @Override
    public boolean put(String key, String value) {
        return put(key, value, DEFAULT_KEY_EXPIRE);
    }

    @Override
    public boolean put(String key, String value, long expire) {
        template.opsForValue().set(key, value, Duration.ofMillis(expire));
        return true;
    }

    @Override
    public <T> boolean put(String key, List<T> list) {
        return put(key, list, DEFAULT_KEY_EXPIRE);
    }

    @Override
    public <T> boolean put(String key, List<T> list, long expire) {
        try {
            template.opsForValue().set(key, mapper.writeValueAsString(list), Duration.ofMillis(expire));
        } catch (JsonProcessingException e) {
            log.error("[{}] 放入缓存出错", key, e);
            return false;
        }
        return true;
    }

    @Override
    public String get(String key) {
        return template.opsForValue().get(key);
    }

    @Override
    public <T> List<T> get(String key, Class<T> clazz) {
        JavaType type = mapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
        try {
            return mapper.readValue(template.opsForValue().get(key), type);
        } catch (Exception e) {
            log.error("查询缓存 [{}] 出错", key, e);
        }
        return null;
    }

    @Override
    public Boolean delete(String key) {
        return template.delete(key);
    }

    @Override
    public Boolean expire(String key, long expire) {
        return template.expire(key, Duration.ofMillis(expire));
    }
}
