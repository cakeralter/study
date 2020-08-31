package cc.caker.boot.repo.mapper;

import cc.caker.boot.repo.entity.Stock;

/**
 * @author cakeralter
 * @date 2020/8/31
 */
public interface StockMapper {

    /**
     * 根据id查询
     *
     * @param sid
     * @return
     */
    Stock findById(long sid);

    /**
     * 售出
     *
     * @param stock
     * @return
     */
    int sale(Stock stock);

    /**
     * 使用乐观锁售出
     *
     * @param stock
     * @return
     */
    int saleByVersion(Stock stock);
}
