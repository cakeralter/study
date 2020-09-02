package cc.caker.boot.controller;

import cc.caker.boot.repo.entity.Stock;
import cc.caker.boot.service.impl.CacheServiceImpl;
import cc.caker.common.vo.ResponseResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cakeralter
 * @date 2020/9/2
 */
@RequiredArgsConstructor
@RestController
public class CacheController {

    private final CacheServiceImpl cacheService;

    @GetMapping("/stock/{sid}")
    public ResponseResult<Stock> stock(@PathVariable Long sid) {
        return ResponseResult.ok(cacheService.query(sid));
    }

    @GetMapping("/stock/cache/{sid}")
    public ResponseResult<Stock> stockCache(@PathVariable Long sid) throws JsonProcessingException {
        return ResponseResult.ok(cacheService.queryByCache(sid));
    }
}
