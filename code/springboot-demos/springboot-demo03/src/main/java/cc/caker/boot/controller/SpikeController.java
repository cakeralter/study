package cc.caker.boot.controller;

import cc.caker.boot.repo.entity.Order;
import cc.caker.boot.service.SpikeService;
import cc.caker.common.vo.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author cakeralter
 * @date 2020/8/31
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class SpikeController {

    private final SpikeService spikeService;

    @GetMapping("/place/{sid}")
    public ResponseResult<Order> placeAnOrder(@PathVariable Long sid) {
        try {
            if (Objects.isNull(sid)) {
                throw new RuntimeException("商品有误[" + sid + "]");
            }
            Order order = spikeService.place(sid);
            log.info("成功购买物品：[{}]，订单号为：[{}]", order.getName(), order.getId());
            return ResponseResult.ok(order);
        } catch (Exception e) {
            log.error("购买失败：[{}]", e.getMessage());
        }
        return ResponseResult.fail();
    }
}
