package cc.caker.boot.service;

import cc.caker.boot.repo.entity.Stock;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author cakeralter
 * @date 2020/9/2
 */
public interface CacheService {

    /**
     * 查询
     *
     * @param sid
     * @return
     */
    Stock query(long sid);

    /**
     * 查询缓存
     *
     * @param sid
     * @return
     */
    Stock queryByCache(long sid) throws JsonProcessingException;
}
