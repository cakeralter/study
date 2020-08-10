package cc.caker.boot.module.um.service.impl;

import cc.caker.boot.module.um.service.AdminResourceService;
import cc.caker.boot.repo.mapper.db1.AdminMapper;
import cc.caker.boot.repo.mapper.db1.AdminResourceMapper;
import cc.caker.boot.repo.model.db1.Admin;
import cc.caker.boot.repo.model.db1.AdminResource;
import cc.caker.boot.repo.model.db1.Resource;
import cc.caker.common.service.RedisService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static cc.caker.boot.constant.Enumerations.Status;
import static cc.caker.boot.constant.RedisConst.UM_ADMIN_RESOURCE;

/**
 * @author cakeralter
 * @date 2020/8/6
 */
@RequiredArgsConstructor
@Service
public class AdminResourceServiceImpl extends ServiceImpl<AdminResourceMapper, AdminResource> implements AdminResourceService {

    private final AdminResourceMapper adminResourceMapper;
    private final AdminMapper adminMapper;
    private final RedisService redisService;

    @Override
    public List<Resource> getResourceByAdminId(Integer adminId) {
        return adminResourceMapper.findByAdminId(adminId, Status.ENABLED.getValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean grantResource(Integer adminId, Integer... resourceIds) {
        if (Objects.isNull(adminId) || Objects.isNull(resourceIds)) {
            return false;
        }
        Admin admin = adminMapper.selectById(adminId);
        if (Objects.isNull(admin)) {
            return false;
        }
        AdminResource adminResource = new AdminResource();
        adminResource.setAdminId(adminId);
        adminResourceMapper.delete(new QueryWrapper<>(adminResource));
        List<AdminResource> adminResources = Arrays.stream(resourceIds).map(x -> {
            AdminResource ar = new AdminResource();
            ar.setAdminId(adminId);
            ar.setResourceId(x);
            return ar;
        }).collect(Collectors.toList());
        boolean result = super.saveBatch(adminResources);
        if (result) {
            redisService.delete(UM_ADMIN_RESOURCE);
        }
        return result;
    }
}
