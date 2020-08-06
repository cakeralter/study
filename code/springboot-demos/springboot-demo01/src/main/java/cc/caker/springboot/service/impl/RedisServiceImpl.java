package cc.caker.springboot.service.impl;

import cc.caker.common.service.RedisService;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

/**
 * @author cakeralter
 * @since 2020/8/6
 */
@RequiredArgsConstructor
@Service
public class RedisServiceImpl implements RedisService {

    private final StringRedisTemplate template;

    @Override
    public boolean put(String key, String value) {
        template.opsForValue().set(key, value);
        return true;
    }

    @Override
    public <T> boolean put(String key, List<T> list) {
        template.opsForValue().set(key, JSONObject.toJSONString(list));
        return false;
    }

    @Override
    public String get(String key) {
        return template.opsForValue().get(key);
    }

    @Override
    public <T> List<T> get(String key, Class<T> clazz) {
        String value = template.opsForValue().get(key);
        return JSONObject.parseArray(value, clazz);
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
