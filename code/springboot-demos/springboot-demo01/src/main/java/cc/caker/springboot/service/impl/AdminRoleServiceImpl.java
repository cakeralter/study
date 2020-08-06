package cc.caker.springboot.service.impl;

import cc.caker.springboot.repo.mapper.db1.AdminRoleMapper;
import cc.caker.springboot.repo.model.db1.AdminRole;
import cc.caker.springboot.repo.model.db1.Role;
import cc.caker.springboot.service.AdminRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户角色表 服务实现类
 *
 * @author cakeralter
 * @since 2020-08-06
 */
@RequiredArgsConstructor
@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements AdminRoleService {

    private final AdminRoleMapper adminRoleMapper;

    @Override
    public List<Role> getRolesByAdmin(Integer id) {
        return adminRoleMapper.findByAdminId(id);
    }
}
