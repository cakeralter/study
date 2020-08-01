package cc.caker.springboot.repo.mapper;

import cc.caker.springboot.repo.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author cakeralter
 * @date 2020/7/23
 */
public interface UserMapper {

    User selectById(@Param("id") Integer id);

    List<User> selectAll();
}
