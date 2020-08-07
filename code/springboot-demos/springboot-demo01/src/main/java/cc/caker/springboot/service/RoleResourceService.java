package cc.caker.springboot.service;


import cc.caker.springboot.repo.model.db1.Resource;
import cc.caker.springboot.repo.model.db1.RoleResource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 角色资源表 服务类
 *
 * @author cakeralter
 * @since 2020-08-06
 */
public interface RoleResourceService extends IService<RoleResource> {

    /**
     * 查询角色资源
     *
     * @param roleId
     * @return
     */
    List<Resource> getResourcesByRoleId(Integer roleId);

    /**
     * 角色授予资源
     *
     * @param roleId
     * @param resourceIds
     * @return
     */
    boolean grantResource(Integer roleId, Integer... resourceIds);
}
