package cc.caker.springboot.service;

import cc.caker.springboot.repo.model.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

/**
 * @author cakeralter
 * @date 2020/7/23
 */
public interface UserService extends IService<User> {

    PageInfo<User> list(Integer page, Integer size);
}
