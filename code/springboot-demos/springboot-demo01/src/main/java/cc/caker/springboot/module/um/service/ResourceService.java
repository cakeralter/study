package cc.caker.springboot.module.um.service;

import cc.caker.springboot.repo.model.db1.Resource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 后台资源表 服务类
 *
 * @author cakeralter
 * @since 2020-08-06
 */
public interface ResourceService extends IService<Resource> {

    /**
     * findAll
     *
     * @return
     */
    List<Resource> findAll();

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    int delete(Integer... ids);
}
