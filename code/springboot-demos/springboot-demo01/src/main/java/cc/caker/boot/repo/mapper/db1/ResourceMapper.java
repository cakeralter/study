package cc.caker.boot.repo.mapper.db1;

import cc.caker.boot.repo.model.db1.Resource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 后台资源表 Mapper 接口
 *
 * @author cakeralter
 * @since 2020-08-06
 */
public interface ResourceMapper extends BaseMapper<Resource> {

    /**
     * 查询所有资源
     *
     * @return
     */
    List<Resource> findAll();

    /**
     * 根据parentId查询
     *
     * @param parentId
     * @return
     */
    List<Resource> findByParentId(Integer parentId);

    /**
     * 查询所有可用资源
     *
     * @return
     */
    List<Resource> findAllEnabled();

    /**
     * 查询最大序号
     *
     * @return
     */
    Integer findMaxSort();
}
