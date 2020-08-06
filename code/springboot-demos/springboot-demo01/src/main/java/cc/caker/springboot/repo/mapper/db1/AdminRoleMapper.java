package cc.caker.springboot.repo.mapper.db1;


import cc.caker.springboot.repo.model.db1.AdminRole;
import cc.caker.springboot.repo.model.db1.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户角色表 Mapper 接口
 *
 * @author cakeralter
 * @since 2020-08-06
 */
public interface AdminRoleMapper extends BaseMapper<AdminRole> {

    /**
     * 通过用户ID查询所有资源
     *
     * @param adminId
     * @return
     */
    List<Role> findByAdminId(@Param("adminId") Integer adminId);
}
