package cc.caker.boot.controller;

import cc.caker.boot.repo.entity.Order;
import cc.caker.boot.service.impl.SpikeUpServiceImpl;
import cc.caker.common.vo.ResponseResult;
import com.google.common.util.concurrent.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 秒杀升级
 *
 * @author cakeralter
 * @date 2020/9/1
 */
@RequiredArgsConstructor
@RestController
public class SpikeUpController {

    private final RateLimiter limiter = RateLimiter.create(10d);
    private final SpikeUpServiceImpl spikeUpService;

    @GetMapping("/place/up/{uid}/{sid}")
    public ResponseResult<Order> placeOrderRate(@PathVariable Long uid, @PathVariable Long sid,
                                                String hash) {
        if (!limiter.tryAcquire()) {
            throw new RuntimeException("你的请求太快，服务器跟不上你的节奏了！");
        }
        return ResponseResult.ok(spikeUpService.place(uid, sid, hash));
    }
}
