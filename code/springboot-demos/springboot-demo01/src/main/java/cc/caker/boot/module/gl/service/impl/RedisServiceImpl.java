package cc.caker.boot.module.gl.service.impl;

import cc.caker.common.service.RedisService;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;

import static cc.caker.boot.constant.RedisConst.DEFAULT_KEY_EXPIRE;

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
    public <T> boolean put(String key, T data) {
        return put(key, data, DEFAULT_KEY_EXPIRE);
    }

    @Override
    public <T> boolean put(String key, T data, long expire) {
        try {
            template.opsForValue().set(key, mapper.writeValueAsString(data), Duration.ofMillis(expire));
        } catch (Exception e) {
            log.error("[{}] 放入缓存出错：{}", key, e.getMessage());
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
            log.error("查询缓存 [{}] 出错：{}", key, e.getMessage());
        }
        return null;
    }

    @Override
    public <K, V> Map<K, V> get(String key, Class<K> kClass, Class<V> vClass) {
        MapType mapType = mapper.getTypeFactory().constructMapType(LinkedHashMap.class, kClass, vClass);
        try {
            return mapper.readValue(template.opsForValue().get(key), mapType);
        } catch (Exception e) {
            log.error("查询缓存 [{}] 出错：{}", key, e.getMessage());
        }
        return null;
    }

    @Override
    public Boolean delete(String key) {
        return template.delete(key);
    }

    @Override
    public Long delete(String... keys) {
        int count = 0;
        for (String key : keys) {
            if (delete(key)) {
                count++;
            }
        }
        return template.delete(Arrays.asList(keys));
    }

    @Override
    public Boolean expire(String key, long expire) {
        return template.expire(key, Duration.ofMillis(expire));
    }
}
