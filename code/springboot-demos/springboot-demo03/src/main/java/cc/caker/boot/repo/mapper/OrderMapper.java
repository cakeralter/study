package cc.caker.boot.repo.mapper;

import cc.caker.boot.repo.entity.Order;

/**
 * @author cakeralter
 * @date 2020/8/31
 */
public interface OrderMapper {

    /**
     * 新建订单
     *
     * @param order
     * @return
     */
    int save(Order order);
}
