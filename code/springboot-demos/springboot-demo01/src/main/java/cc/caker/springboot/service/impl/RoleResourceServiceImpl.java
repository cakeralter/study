package cc.caker.springboot.service.impl;

import cc.caker.springboot.repo.mapper.db1.RoleResourceMapper;
import cc.caker.springboot.repo.model.db1.RoleResource;
import cc.caker.springboot.service.RoleResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 角色资源表 服务实现类
 *
 * @author cakeralter
 * @since 2020-08-06
 */
@Service
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceMapper, RoleResource> implements RoleResourceService {
}
