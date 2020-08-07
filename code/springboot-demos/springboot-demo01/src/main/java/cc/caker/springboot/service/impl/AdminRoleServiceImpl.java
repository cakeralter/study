package cc.caker.springboot.service.impl;

import cc.caker.springboot.repo.mapper.db1.AdminMapper;
import cc.caker.springboot.repo.mapper.db1.AdminRoleMapper;
import cc.caker.springboot.repo.model.db1.Admin;
import cc.caker.springboot.repo.model.db1.AdminRole;
import cc.caker.springboot.repo.model.db1.Role;
import cc.caker.springboot.service.AdminRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @Override
    public List<Role> getRolesByAdmin(Integer id) {
        return adminRoleMapper.findByAdminId(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean grant(Integer adminId, Integer... roleIds) {
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
        return super.saveBatch(adminRoles);
    }
}
