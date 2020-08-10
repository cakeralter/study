package cc.caker.boot.module.um.service.impl;

import cc.caker.boot.module.um.service.RoleService;
import cc.caker.boot.repo.mapper.db1.RoleMapper;
import cc.caker.boot.repo.model.db1.Role;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static cc.caker.boot.constant.Enumerations.Status;

/**
 * 角色表 服务实现类
 *
 * @author cakeralter
 * @since 2020-08-06
 */
@RequiredArgsConstructor
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    private final RoleMapper roleMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Integer... ids) {
        int count = 0;
        Role role = new Role();
        role.setStatus(Status.DISABLED.getValue());
        for (Integer id : ids) {
            role.setId(id);
            count += roleMapper.updateById(role);
        }
        return count;
    }
}
