package cc.caker.springboot.service.impl;

import cc.caker.springboot.repo.mapper.UserMapper;
import cc.caker.springboot.repo.model.User;
import cc.caker.springboot.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author cakeralter
 * @date 2020/7/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
