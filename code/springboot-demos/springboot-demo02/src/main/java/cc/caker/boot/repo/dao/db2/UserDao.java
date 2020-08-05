package cc.caker.boot.repo.dao.db2;

import cc.caker.boot.repo.entity.db2.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author cakeralter
 * @date 2020/8/5
 */
public interface UserDao extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
}
