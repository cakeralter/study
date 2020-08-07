package cc.caker.springboot.service.impl;

import cc.caker.springboot.repo.mapper.db1.RoleMapper;
import cc.caker.springboot.repo.model.db1.Role;
import cc.caker.springboot.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

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
    public int delete(Integer[] ids) {
        return roleMapper.deleteBatchIds(Arrays.asList(ids));
    }
}
