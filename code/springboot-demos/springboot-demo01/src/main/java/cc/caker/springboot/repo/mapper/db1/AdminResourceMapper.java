package cc.caker.springboot.repo.mapper.db1;

import cc.caker.springboot.repo.model.db1.AdminResource;
import cc.caker.springboot.repo.model.db1.Resource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @author cakeralter
 * @date 2020/8/6
 */
public interface AdminResourceMapper extends BaseMapper<AdminResource> {

    /**
     * 查询用户资源(根据资源状态)
     *
     * @param adminId
     * @return
     */
    List<Resource> findByAdminId(@Param("adminId") Integer adminId, @Param("state") Integer state);

    /**
     * 查询用户资源code
     *
     * @param adminId
     * @return
     */
    Set<String> findCodeByAdminId(@Param("adminId") Integer adminId);
}
