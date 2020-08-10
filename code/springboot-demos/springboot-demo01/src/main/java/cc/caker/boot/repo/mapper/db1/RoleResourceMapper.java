package cc.caker.boot.repo.mapper.db1;


import cc.caker.boot.repo.model.db1.Resource;
import cc.caker.boot.repo.model.db1.RoleResource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 角色资源表 Mapper 接口
 *
 * @author cakeralter
 * @since 2020-08-06
 */
public interface RoleResourceMapper extends BaseMapper<RoleResource> {

    /**
     * 根据roleId查询
     *
     * @param roleId
     * @return
     */
    List<Resource> findByRoleId(Integer roleId);
}
