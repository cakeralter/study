package cc.caker.springboot.repo.mapper.db1;


import cc.caker.springboot.repo.model.db1.AdminRole;
import cc.caker.springboot.repo.model.db1.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 用户角色表 Mapper 接口
 *
 * @author cakeralter
 * @since 2020-08-06
 */
public interface AdminRoleMapper extends BaseMapper<AdminRole> {

    /**
     * 通过用户ID查询所有角色
     *
     * @param adminId
     * @return
     */
    List<Role> findRoles(@Param("adminId") Integer adminId);

    /**
     * 通过用户ID查询所有角色CODE
     *
     * @param adminId
     * @return
     */
    Set<String> findRolesByAdminId(@Param("adminId") Integer adminId);
}
