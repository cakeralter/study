package cc.caker.springboot.service.impl;

import cc.caker.springboot.repo.mapper.UserMapper;
import cc.caker.springboot.repo.model.User;
import cc.caker.springboot.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @Override
    public PageInfo<User> list(Integer page, Integer size) {
        // 开启分页
        PageHelper.startPage(page, size);
        return PageInfo.of(userMapper.selectAll());
    }
}
