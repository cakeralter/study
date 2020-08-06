package cc.caker.springboot.service.impl;

import cc.caker.springboot.repo.mapper.db1.RoleMapper;
import cc.caker.springboot.repo.model.db1.Role;
import cc.caker.springboot.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 角色表 服务实现类
 *
 * @author cakeralter
 * @since 2020-08-06
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
}
