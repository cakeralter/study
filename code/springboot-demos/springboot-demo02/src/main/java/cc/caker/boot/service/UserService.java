package cc.caker.boot.service;

import cc.caker.boot.repo.dao.db2.UserDao;
import cc.caker.boot.repo.entity.db2.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author cakeralter
 * @date 2020/8/5
 */
@Transactional(rollbackFor = Exception.class, transactionManager = "db2TransactionManager")
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User user(Integer id) {
        return userDao.findById(id).get();
    }

    public Page<User> page(Integer page, Integer size) {
        return userDao.findAll(PageRequest.of(page - 1, size));
    }
}
