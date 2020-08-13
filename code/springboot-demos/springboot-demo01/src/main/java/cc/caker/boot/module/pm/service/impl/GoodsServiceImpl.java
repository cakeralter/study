package cc.caker.boot.module.pm.service.impl;

import cc.caker.boot.module.pm.service.GoodsService;
import cc.caker.boot.repo.mapper.db2.GoodsMapper;
import cc.caker.boot.repo.model.db2.Goods;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author cakeralter
 * @date 2020/8/13
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
}
