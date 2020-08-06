package cc.caker.springboot.service.impl;

import cc.caker.springboot.repo.mapper.db1.ResourceMapper;
import cc.caker.springboot.repo.model.db1.Resource;
import cc.caker.springboot.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 后台资源表 服务实现类
 *
 * @author cakeralter
 * @since 2020-08-06
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {
}
