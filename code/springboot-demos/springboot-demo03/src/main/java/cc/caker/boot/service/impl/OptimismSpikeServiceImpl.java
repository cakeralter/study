package cc.caker.boot.service.impl;

import cc.caker.boot.repo.entity.Order;
import cc.caker.boot.repo.entity.Stock;
import cc.caker.boot.repo.mapper.OrderMapper;
import cc.caker.boot.repo.mapper.StockMapper;
import cc.caker.boot.service.SpikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 乐观锁秒杀服务
 *
 * @author cakeralter
 * @date 2020/9/1
 */
@RequiredArgsConstructor
@Service
public class OptimismSpikeServiceImpl implements SpikeService {

    private final OrderMapper orderMapper;
    private final StockMapper stockMapper;

    @Override
    public Stock checkStock(long sid) {
        Stock stock = stockMapper.findById(sid);
        if (Objects.isNull(stock)) {
            throw new RuntimeException("暂无库存");
        }
        if (stock.getSale() >= stock.getCount()) {
            throw new RuntimeException("库存已售空");
        }
        return stock;
    }

    /**
     * 乐观锁扣除库存
     *
     * @param stock
     * @return
     */
    @Override
    public int saleStock(Stock stock) {
        int count = stockMapper.saleByVersion(stock);
        if (count == 0) {
            throw new RuntimeException("人太多了，没抢到！");
        }
        return count;
    }

    @Override
    public Order createOrder(Stock stock) {
        Order order = Order.builder()
                .name(stock.getName())
                .sid(stock.getId())
                .build();
        orderMapper.save(order);
        return order;
    }
}
