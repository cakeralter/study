package cc.caker.springboot.service;

import cc.caker.springboot.repo.model.User;

/**
 * @author cakeralter
 * @date 2020/7/23
 */
public interface UserService {

    User selectById(Integer id);
}
