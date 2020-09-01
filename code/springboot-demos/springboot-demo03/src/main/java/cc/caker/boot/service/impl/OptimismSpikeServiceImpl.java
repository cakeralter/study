package cc.caker.boot.service.impl;

import cc.caker.boot.repo.entity.Order;
import cc.caker.boot.repo.entity.Stock;
import cc.caker.boot.repo.mapper.OrderMapper;
import cc.caker.boot.repo.mapper.StockMapper;
import cc.caker.boot.service.SpikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
