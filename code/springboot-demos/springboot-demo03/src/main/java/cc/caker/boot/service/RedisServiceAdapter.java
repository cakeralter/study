package cc.caker.boot.service;

import cc.caker.common.service.RedisService;

import java.util.List;
import java.util.Map;

/**
 * @author cakeralter
 * @date 2020/9/1
 */
public abstract class RedisServiceAdapter implements RedisService {

    @Override
    public boolean put(String key, String value) {
        return false;
    }

    @Override
    public boolean put(String key, String value, long expire) {
        return false;
    }

    @Override
    public <T> boolean put(String key, T data) {
        return false;
    }

    @Override
    public <T> boolean put(String key, T data, long expire) {
        return false;
    }

    @Override
    public String get(String key) {
        return null;
    }

    @Override
    public <T> List<T> get(String key, Class<T> clazz) {
        return null;
    }

    @Override
    public <K, V> Map<K, V> get(String key, Class<K> kClass, Class<V> vClass) {
        return null;
    }

    @Override
    public Boolean delete(String key) {
        return null;
    }

    @Override
    public Long delete(String... keys) {
        return null;
    }

    @Override
    public Boolean expire(String key, long expire) {
        return null;
    }
}
