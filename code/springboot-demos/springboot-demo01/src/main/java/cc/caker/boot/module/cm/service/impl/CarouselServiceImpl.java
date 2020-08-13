package cc.caker.boot.module.cm.service.impl;

import cc.caker.boot.module.cm.service.CarouselService;
import cc.caker.boot.repo.mapper.db1.CarouselMapper;
import cc.caker.boot.repo.model.db1.Carousel;
import cc.caker.common.service.RedisService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author cakeralter
 * @date 2020/8/13
 */
@RequiredArgsConstructor
@Service
public class CarouselServiceImpl extends ServiceImpl<CarouselMapper, Carousel> implements CarouselService {

    private final RedisService redisService;
    private final CarouselMapper carouselMapper;
}
