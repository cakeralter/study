package cc.caker.springboot.module.um.service.impl;

import cc.caker.springboot.constant.Enumerations;
import cc.caker.springboot.module.um.service.ResourceService;
import cc.caker.springboot.repo.mapper.db1.ResourceMapper;
import cc.caker.springboot.repo.model.db1.Resource;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Cacheable(value = "resource", unless = "#result == null")
    @Override
    public List<Resource> findAll() {
        return resourceMapper.findAll();
    }

    @CacheEvict(value = "resource", allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Integer... ids) {
        int count = 0;
        Resource resource = new Resource();
        resource.setStatus(Enumerations.Status.DISABLED.getValue());
        for (Integer id : ids) {
            resource.setId(id);
            count += resourceMapper.updateById(resource);
        }
        return count;
    }

    @Cacheable(value = "resource", unless = "#result == null")
    @Override
    public List<Resource> loadAllResources() {

        return null;
    }
}
