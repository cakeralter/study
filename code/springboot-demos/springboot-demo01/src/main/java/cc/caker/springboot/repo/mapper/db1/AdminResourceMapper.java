package cc.caker.springboot.repo.mapper.db1;

import cc.caker.springboot.repo.model.db1.AdminResource;
import cc.caker.springboot.repo.model.db1.Resource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author cakeralter
 * @date 2020/8/6
 */
public interface AdminResourceMapper extends BaseMapper<AdminResource> {

    /**
     * 查询用户资源
     *
     * @param adminId
     * @return
     */
    List<Resource> findByAdminId(Integer adminId);
}
