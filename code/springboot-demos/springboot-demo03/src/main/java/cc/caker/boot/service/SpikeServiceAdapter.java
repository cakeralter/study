package cc.caker.boot.service;

import cc.caker.boot.repo.entity.Order;
import cc.caker.boot.repo.entity.Stock;

/**
 * @author cakeralter
 * @date 2020/9/1
 */
public abstract class SpikeServiceAdapter implements SpikeService {

    @Override
    public Stock checkStock(long sid) {
        return null;
    }

    @Override
    public int saleStock(Stock stock) {
        return 0;
    }

    @Override
    public Order createOrder(Stock stock) {
        return null;
    }
}
