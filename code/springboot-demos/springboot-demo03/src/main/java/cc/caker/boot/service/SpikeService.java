package cc.caker.boot.service;

import cc.caker.boot.repo.entity.Order;
import cc.caker.boot.repo.entity.Stock;
import cc.caker.boot.repo.mapper.OrderMapper;
import cc.caker.boot.repo.mapper.StockMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * 秒杀测试：使用jmeter模拟1000个用户抢购100个商品
 * <p>
 * I. 无并发保护措施：售出商品20，创建订单1000
 * II. 使用事务：售出商品100，创建订单1000
 * III. 乐观锁：售出商品46，创建订单46
 *
 * @author cakeralter
 * @date 2020/8/31
 */
@RequiredArgsConstructor
@Service
public class SpikeService {

    private final OrderMapper orderMapper;
    private final StockMapper stockMapper;

    /**
     * 下单
     *
     * @param sid
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Order place(long sid) {
        // 校验库存
        Stock stock = checkStock(sid);
        // 扣库存
//        saleStock(stock);
        saleStockByVersion(stock);
        // 创建订单
        return createOrder(stock);
    }

    /**
     * 校验库存
     *
     * @param sid
     * @return
     */
    private Stock checkStock(long sid) {
        Stock stock = stockMapper.findById(sid);
        if (Objects.isNull(stock)) {
            throw new RuntimeException("库存有误");
        }
        if (stock.getSale() >= stock.getCount()) {
            throw new RuntimeException("已售空");
        }
        return stock;
    }

    /**
     * 售出
     *
     * @param stock
     * @return
     */
    private int saleStock(Stock stock) {
        stock.setSale(stock.getSale() + 1);
        return stockMapper.sale(stock);
    }

    /**
     * 乐观锁售出
     *
     * @param stock
     * @return
     */
    private int saleStockByVersion(Stock stock) {
        int count = stockMapper.saleByVersion(stock);
        if (count == 0) {
            throw new RuntimeException("并发更新失败");
        }
        return count;
    }

    /**
     * 创建订单
     *
     * @param stock
     * @return
     */
    private Order createOrder(Stock stock) {
        Order order = Order.builder()
                .name(stock.getName())
                .sid(stock.getId())
                .build();
        orderMapper.save(order);
        return order;
    }
}
