package cc.caker.boot.module.um.service.impl;

import cc.caker.boot.constant.Enumerations;
import cc.caker.boot.module.um.service.ResourceService;
import cc.caker.boot.repo.mapper.db1.ResourceMapper;
import cc.caker.boot.repo.model.db1.Resource;
import cc.caker.common.service.RedisService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static cc.caker.boot.constant.RedisConst.UM_RESOURCES_ALL;
import static cc.caker.boot.constant.RedisConst.UM_RESOURCES_ENABLED_ALL;

/**
 * 后台资源表 服务实现类
 *
 * @author cakeralter
 * @since 2020-08-06
 */
@RequiredArgsConstructor
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    private final Map<String, String> DEFAULT_PERMISSIONS = new LinkedHashMap<>();

    {
        DEFAULT_PERMISSIONS.put("/logout", "anon");
        DEFAULT_PERMISSIONS.put("/login", "anon");
        DEFAULT_PERMISSIONS.put("/swagger**/**", "anon");
        DEFAULT_PERMISSIONS.put("/v3/**", "anon");
        DEFAULT_PERMISSIONS.put("/**", "authc");
    }

    private final ResourceMapper resourceMapper;
    private final RedisService redisService;

    @Override
    public List<Resource> findAll() {
        List<Resource> resources = redisService.get(UM_RESOURCES_ALL, Resource.class);
        if (Objects.isNull(resources)) {
            resources = resourceMapper.findAll();
            redisService.put(UM_RESOURCES_ALL, resources);
        }
        return resources;
    }

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
        if (count > 0) {
            redisService.delete(UM_RESOURCES_ALL, UM_RESOURCES_ENABLED_ALL);
        }
        return count;
    }

    @Override
    public Map<String, String> loadAllResources() {
        Map<String, String> chains = redisService.get(UM_RESOURCES_ENABLED_ALL, String.class, String.class);
        if (Objects.isNull(chains)) {
            List<Resource> resources = resourceMapper.findAllEnabled();
            chains = new LinkedHashMap<>(DEFAULT_PERMISSIONS);
            for (Resource resource : resources) {
                String allow = String.format("authc, perms[%s]", resource.getCode());
                chains.put(resource.getUri(), allow);
            }
            redisService.put(UM_RESOURCES_ENABLED_ALL, chains);
        }
        return chains;
    }
}
