package cc.caker.springboot.module.um.service.impl;

import cc.caker.common.service.RedisService;
import cc.caker.springboot.component.PermissionFlushEvent;
import cc.caker.springboot.module.um.service.AdminRoleService;
import cc.caker.springboot.repo.mapper.db1.AdminMapper;
import cc.caker.springboot.repo.mapper.db1.AdminRoleMapper;
import cc.caker.springboot.repo.model.db1.Admin;
import cc.caker.springboot.repo.model.db1.AdminRole;
import cc.caker.springboot.repo.model.db1.Role;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static cc.caker.springboot.constant.RedisConstant.UM_ADMIN_ROLE;

/**
 * 用户角色表 服务实现类
 *
 * @author cakeralter
 * @since 2020-08-06
 */
@RequiredArgsConstructor
@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements AdminRoleService {

    private final AdminMapper adminMapper;
    private final AdminRoleMapper adminRoleMapper;
    private final RedisService redisService;
    private final ApplicationContext applicationContext;

    @Override
    public List<Role> getRolesByAdmin(Integer id) {
        return adminRoleMapper.findRoles(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean grantRole(Integer adminId, Integer... roleIds) {
        if (Objects.isNull(adminId) || Objects.isNull(roleIds)) {
            return false;
        }
        Admin admin = adminMapper.selectById(adminId);
        if (Objects.isNull(admin)) {
            return false;
        }
        AdminRole adminRole = new AdminRole();
        adminRole.setAdminId(adminId);
        adminRoleMapper.delete(new QueryWrapper<>(adminRole));
        List<AdminRole> adminRoles = Arrays.stream(roleIds).map(x -> {
            AdminRole ar = new AdminRole();
            ar.setAdminId(adminId);
            ar.setRoleId(x);
            return ar;
        }).collect(Collectors.toList());
        boolean result = super.saveBatch(adminRoles);
        if (result) {
            redisService.delete(UM_ADMIN_ROLE);
            applicationContext.publishEvent(new PermissionFlushEvent());
        }
        return result;
    }
}
