package cc.caker.boot.service;

import cc.caker.boot.repo.entity.Order;
import cc.caker.boot.repo.entity.Stock;

/**
 * @author cakeralter
 * @date 2020/9/1
 */
public interface SpikeService {

    /**
     * 秒杀下单
     *
     * @param sid
     * @return
     */
    default Order place(long sid) {
        // 校验库存
        Stock stock = checkStock(sid);
        // 扣除库存
        saleStock(stock);
        // 生成订单
        return createOrder(stock);
    }

    /**
     * 校验库存
     *
     * @param sid
     * @return
     */
    Stock checkStock(long sid);

    /**
     * 扣除库存
     *
     * @param stock
     * @return
     */
    int saleStock(Stock stock);

    /**
     * 生成订单
     *
     * @param stock
     * @return
     */
    Order createOrder(Stock stock);
}
