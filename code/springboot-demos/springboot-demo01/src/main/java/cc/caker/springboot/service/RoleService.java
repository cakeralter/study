package cc.caker.springboot.service;

import cc.caker.springboot.repo.model.db1.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 角色表 服务类
 *
 * @author cakeralter
 * @since 2020-08-06
 */
public interface RoleService extends IService<Role> {

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    int delete(Integer[] ids);
}
