package cc.caker.boot.service.impl;

import cc.caker.boot.constant.RedisEnum;
import cc.caker.boot.repo.entity.Order;
import cc.caker.boot.repo.entity.Stock;
import cc.caker.boot.repo.mapper.OrderMapper;
import cc.caker.boot.repo.mapper.StockMapper;
import cc.caker.common.service.RedisService;
import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author cakeralter
 * @date 2020/9/1
 */
@RequiredArgsConstructor
@Service
public class SpikeUpServiceImpl {

    private final RedisService redisService;
    private final StockMapper stockMapper;
    private final OrderMapper orderMapper;

    public Order place(Long uid, Long sid, String hash) {
        if (Objects.isNull(uid) || Objects.isNull(sid) || Strings.isNullOrEmpty(hash)) {
            throw new RuntimeException("信息有误，请重新尝试！");
        }
        String key = String.format("%s::%d::%d", RedisEnum.SPIKE_HASH_KEY.getKey(), uid, sid);
        String value = redisService.get(key);
        if (!Objects.equals(hash, value)) {
            throw new RuntimeException("校验码有误，请刷新当前页面！");
        }

        // 校验库存
        Stock stock = checkStock(sid);
        // 扣除库存
        saleStock(stock);
        // 生成订单
        return createOrder(stock, uid);
    }

    private Stock checkStock(long sid) {
        Stock stock = stockMapper.findById(sid);
        if (Objects.isNull(stock)) {
            throw new RuntimeException("暂无库存");
        }
        if (stock.getSale() >= stock.getCount()) {
            throw new RuntimeException("库存已售空");
        }
        return stock;
    }

    private int saleStock(Stock stock) {
        int count = stockMapper.saleByVersion(stock);
        if (count == 0) {
            throw new RuntimeException("人太多了，没抢到！");
        }
        return count;
    }

    private Order createOrder(Stock stock, long uid) {
        Order order = Order.builder()
                .name(stock.getName())
                .sid(stock.getId())
                .userId(uid)
                .build();
        orderMapper.save(order);
        return order;
    }
}
