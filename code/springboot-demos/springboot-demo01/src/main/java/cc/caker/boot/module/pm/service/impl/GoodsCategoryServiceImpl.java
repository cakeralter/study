package cc.caker.boot.module.pm.service.impl;

import cc.caker.boot.module.pm.service.GoodsCategoryService;
import cc.caker.boot.repo.mapper.db2.GoodsCategoryMapper;
import cc.caker.boot.repo.model.db2.GoodsCategory;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author cakeralter
 * @date 2020/8/13
 */
@Service
public class GoodsCategoryServiceImpl extends ServiceImpl<GoodsCategoryMapper, GoodsCategory> implements GoodsCategoryService {
}
