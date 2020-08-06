package cc.caker.common.service;

import java.util.List;

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
     * put list string
     *
     * @param key
     * @param list
     * @return
     */
    <T> boolean put(final String key, List<T> list);

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
     * delete key
     *
     * @param key
     * @return
     */
    Boolean delete(final String key);

    /**
     * 设置过期时间
     *
     * @param key
     * @param expire
     * @return
     */
    Boolean expire(final String key, long expire);
}
