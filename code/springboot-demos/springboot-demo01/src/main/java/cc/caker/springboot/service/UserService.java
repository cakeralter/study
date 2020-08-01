package cc.caker.springboot.service;

import cc.caker.springboot.repo.model.User;
import com.github.pagehelper.PageInfo;

/**
 * @author cakeralter
 * @date 2020/7/23
 */
public interface UserService {

    User selectById(Integer id);

    PageInfo<User> list(Integer page, Integer size);
}
