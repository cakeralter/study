package cc.caker.springboot.service.impl;

import cc.caker.springboot.repo.mapper.db1.ResourceMapper;
import cc.caker.springboot.repo.model.db1.Resource;
import cc.caker.springboot.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 后台资源表 服务实现类
 *
 * @author cakeralter
 * @since 2020-08-06
 */
@RequiredArgsConstructor
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    private final ResourceMapper resourceMapper;

    @Override
    public List<Resource> findAll() {
        return resourceMapper.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Integer[] ids) {
        return resourceMapper.deleteBatchIds(Arrays.asList(ids));
    }
}
