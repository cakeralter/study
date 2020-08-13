package cc.caker.boot.repo.mapper.db1;

import cc.caker.boot.repo.model.db1.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 用户表 Mapper 接口
 *
 * @author cakeralter
 * @since 2020-08-06
 */
public interface AdminMapper extends BaseMapper<Admin> {

    /**
     * 根据用户名查询
     *
     * @param username
     * @return
     */
    Admin findByUsername(String username);

    /**
     * 查询最大排序
     *
     * @return
     */
    Integer findMaxSort();
}
