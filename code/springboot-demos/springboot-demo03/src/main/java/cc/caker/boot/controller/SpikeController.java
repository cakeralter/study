package cc.caker.boot.controller;

import cc.caker.boot.repo.entity.Order;
import cc.caker.boot.service.impl.OptimismSpikeServiceImpl;
import cc.caker.boot.service.impl.PessimisticSpikeServiceImpl;
import cc.caker.boot.service.impl.SpikeServiceImpl;
import cc.caker.boot.service.impl.TransactionalSpikeServiceImpl;
import cc.caker.common.vo.ResponseResult;
import com.google.common.util.concurrent.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 秒杀测试：使用jmeter模拟500个用户抢购50个商品
 * <p>
 * I. 无并发保护措施：售出商品14，创建订单500
 * II. 使用事务：售出商品50，创建订单487
 * III. 乐观锁：售出商品24，创建订单24
 * IV. 悲观锁 + 事务：售出商品50，创建订单50
 * V. 乐观锁 + 限流：售出商品9，创建订单9
 *
 * @author cakeralter
 * @date 2020/8/31
 */
@RequiredArgsConstructor
@RestController
public class SpikeController {

    /**
     * 限制每秒10个请求
     */
    private final RateLimiter limiter = RateLimiter.create(10d);
    private final SpikeServiceImpl spikeService;
    private final TransactionalSpikeServiceImpl transactionalSpikeService;
    private final OptimismSpikeServiceImpl optimismSpikeService;
    private final PessimisticSpikeServiceImpl pessimisticSpikeService;

    /**
     * 秒杀
     *
     * @param sid
     * @return
     */
    @GetMapping("/place/{sid}")
    public ResponseResult<Order> placeOrder(@PathVariable Long sid) {
        return ResponseResult.ok(spikeService.place(sid));
    }

    /**
     * 事务秒杀
     *
     * @param sid
     * @return
     */
    @GetMapping("/place/transactional/{sid}")
    public ResponseResult<Order> placeOrderTransactional(@PathVariable Long sid) {
        return ResponseResult.ok(transactionalSpikeService.place(sid));
    }

    /**
     * 乐观锁秒杀
     *
     * @param sid
     * @return
     */
    @GetMapping("/place/optimism/{sid}")
    public ResponseResult<Order> placeOrderOptimism(@PathVariable Long sid) {
        return ResponseResult.ok(optimismSpikeService.place(sid));
    }

    /**
     * 悲观锁秒杀
     *
     * @param sid
     * @return
     */
    @GetMapping("/place/pessimistic/{sid}")
    public ResponseResult<Order> placeOrderPessimistic(@PathVariable Long sid) {
        return ResponseResult.ok(pessimisticSpikeService.place(sid));
    }

    /**
     * 乐观锁 + 限流秒杀
     *
     * @param sid
     * @return
     */
    @GetMapping("/place/rate/{sid}")
    public ResponseResult<Order> placeOrderRate(@PathVariable Long sid) {
        if (!limiter.tryAcquire()) {
            throw new RuntimeException("你的请求太快，服务器跟不上你的节奏了！");
        }
        return ResponseResult.ok(optimismSpikeService.place(sid));
    }
}
