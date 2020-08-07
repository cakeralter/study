package cc.caker.springboot.service;

import cc.caker.springboot.repo.model.db1.AdminResource;
import cc.caker.springboot.repo.model.db1.Resource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author cakeralter
 * @date 2020/8/6
 */
public interface AdminResourceService extends IService<AdminResource> {

    /**
     * 查询用户资源
     *
     * @param adminId
     * @return
     */
    List<Resource> getResourceByAdminId(Integer adminId);

    /**
     * 授予用户资源
     *
     * @param adminId
     * @param resourceIds
     * @return
     */
    boolean grantResource(Integer adminId, Integer... resourceIds);
}
