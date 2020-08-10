package cc.caker.boot.module.um.service;

import cc.caker.boot.repo.model.db1.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 用户表 服务类
 *
 * @author cakeralter
 * @since 2020-08-06
 */
public interface AdminService extends IService<Admin> {

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    int delete(Integer... ids);

    /**
     * 修改密码
     *
     * @param adminId
     * @param password
     * @return
     */
    boolean changePassword(String adminId, String password);
}
