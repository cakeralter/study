package cc.caker.springboot.module.um.service.impl;

import cc.caker.common.service.RedisService;
import cc.caker.springboot.module.um.service.RoleResourceService;
import cc.caker.springboot.repo.mapper.db1.RoleMapper;
import cc.caker.springboot.repo.mapper.db1.RoleResourceMapper;
import cc.caker.springboot.repo.model.db1.Resource;
import cc.caker.springboot.repo.model.db1.Role;
import cc.caker.springboot.repo.model.db1.RoleResource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static cc.caker.springboot.constant.RedisConst.UM_ROLE_RESOURCE;

/**
 * 角色资源表 服务实现类
 *
 * @author cakeralter
 * @since 2020-08-06
 */
@RequiredArgsConstructor
@Service
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceMapper, RoleResource> implements RoleResourceService {

    private final RoleResourceMapper roleResourceMapper;
    private final RoleMapper roleMapper;
    private final RedisService redisService;

    @Override
    public List<Resource> getResourcesByRoleId(Integer roleId) {
        return roleResourceMapper.findByRoleId(roleId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean grantResource(Integer roleId, Integer... resourceIds) {
        if (Objects.isNull(roleId) || Objects.isNull(resourceIds)) {
            return false;
        }
        Role role = roleMapper.selectById(roleId);
        if (Objects.isNull(role)) {
            return false;
        }
        RoleResource roleResource = new RoleResource();
        roleResource.setRoleId(roleId);
        roleResourceMapper.delete(new QueryWrapper<>(roleResource));
        List<RoleResource> roleResources = Arrays.stream(resourceIds).map(x -> {
            RoleResource rr = new RoleResource();
            rr.setRoleId(roleId);
            rr.setResourceId(x);
            return rr;
        }).collect(Collectors.toList());
        boolean result = super.saveBatch(roleResources);
        if (result) {
            redisService.delete(UM_ROLE_RESOURCE);
        }
        return result;
    }
}
