package cc.caker.boot.service.impl;

import cc.caker.boot.config.RedisConfig;
import cc.caker.boot.constant.RedisEnum;
import cc.caker.boot.repo.entity.Stock;
import cc.caker.boot.repo.mapper.StockMapper;
import cc.caker.boot.service.CacheService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author cakeralter
 * @date 2020/9/2
 */
@RequiredArgsConstructor
@Service
public class CacheServiceImpl implements CacheService {

    private final StockMapper stockMapper;
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public Stock query(long sid) {
        return stockMapper.findById(sid);
    }

    @Override
    public Stock queryByCache(long sid) throws JsonProcessingException {
        String key = String.format("%s::%d", RedisEnum.STOCK_CACHE_KEY.getKey(), sid);
        String content = redisTemplate.opsForValue().get(key);
        if (Strings.isNullOrEmpty(content)) {
            Stock stock = stockMapper.findById(sid);
            redisTemplate.opsForValue().set(key, RedisConfig.getMapper().writeValueAsString(stock),
                    UserServiceImpl.DEFAULT_EXPIRE, TimeUnit.SECONDS);
            return stock;
        }
        return RedisConfig.getMapper().readValue(content, Stock.class);
    }
}
