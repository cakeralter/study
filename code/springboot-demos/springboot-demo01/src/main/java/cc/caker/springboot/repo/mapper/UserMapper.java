package cc.caker.springboot.repo.mapper;

import cc.caker.springboot.repo.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author cakeralter
 * @date 2020/7/23
 */
public interface UserMapper extends BaseMapper<User> {

    List<User> selectAll();
}
