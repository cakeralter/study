package cc.caker.springboot.service;

import cc.caker.springboot.repo.model.db1.AdminRole;
import cc.caker.springboot.repo.model.db1.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 用户角色表 服务类
 *
 * @author cakeralter
 * @since 2020-08-06
 */
public interface AdminRoleService extends IService<AdminRole> {

    /**
     * 获取用户角色
     *
     * @param id
     * @return
     */
    List<Role> getRolesByAdmin(Integer id);
}
