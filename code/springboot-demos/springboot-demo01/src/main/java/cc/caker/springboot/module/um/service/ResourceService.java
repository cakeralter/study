package cc.caker.springboot.module.um.service;

import cc.caker.springboot.repo.model.db1.Resource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

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

    /**
     * 加载所有可用资源
     *
     * @return
     */
    Map<String, String> loadAllResources();
}
