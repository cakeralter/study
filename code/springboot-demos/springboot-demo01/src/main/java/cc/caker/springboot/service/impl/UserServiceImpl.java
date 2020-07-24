package cc.caker.springboot.service.impl;

import cc.caker.springboot.repo.mapper.UserMapper;
import cc.caker.springboot.repo.model.User;
import cc.caker.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author cakeralter
 * @date 2020/7/23
 */
@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }
}
