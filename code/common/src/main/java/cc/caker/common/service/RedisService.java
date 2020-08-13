package cc.caker.common.service;

import java.util.List;
import java.util.Map;

/**
 * Redis操作接口
 *
 * @author cakeralter
 * @since 2020/8/6
 */
public interface RedisService {

    /**
     * put string
     *
     * @param key
     * @param value
     * @return
     */
    boolean put(final String key, String value);

    /**
     * put string
     *
     * @param key
     * @param value
     * @param expire
     * @return
     */
    boolean put(final String key, String value, long expire);

    /**
     * put T
     *
     * @param key
     * @param data
     * @return
     */
    <T> boolean put(final String key, T data);

    /**
     * put T
     *
     * @param key
     * @param data
     * @param expire
     * @param <T>
     * @return
     */
    <T> boolean put(final String key, T data, long expire);

    /**
     * get string
     *
     * @param key
     * @return
     */
    String get(final String key);

    /**
     * get list string
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    <T> List<T> get(final String key, Class<T> clazz);

    /**
     * get map
     *
     * @param key
     * @param kClass
     * @param vClass
     * @param <K>
     * @param <V>
     * @return
     */
    <K, V> Map<K, V> get(final String key, Class<K> kClass, Class<V> vClass);

    /**
     * delete key
     *
     * @param key
     * @return
     */
    Boolean delete(final String key);

    /**
     * delete keys
     *
     * @param keys
     * @return
     */
    Long delete(final String... keys);

    /**
     * 设置过期时间
     *
     * @param key
     * @param expire
     * @return
     */
    Boolean expire(final String key, long expire);
}
