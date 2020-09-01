package cc.caker.boot.service.impl;

import cc.caker.boot.repo.entity.Order;
import cc.caker.boot.repo.entity.Stock;
import cc.caker.boot.repo.mapper.OrderMapper;
import cc.caker.boot.repo.mapper.StockMapper;
import cc.caker.boot.service.SpikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * 事务秒杀
 *
 * @author cakeralter
 * @date 2020/9/1
 */
@RequiredArgsConstructor
@Service
public class TransactionalSpikeServiceImpl implements SpikeService {

    private final OrderMapper orderMapper;
    private final StockMapper stockMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Order place(long sid) {
        // 校验库存
        Stock stock = checkStock(sid);
        // 扣除库存
        saleStock(stock);
        // 生成订单
        return createOrder(stock);
    }

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

    @Override
    public int saleStock(Stock stock) {
        stock.setSale(stock.getSale() + 1);
        return stockMapper.sale(stock);
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
