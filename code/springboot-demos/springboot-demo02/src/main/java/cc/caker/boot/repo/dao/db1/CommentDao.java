package cc.caker.boot.repo.dao.db1;

import cc.caker.boot.repo.entity.db1.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author cakeralter
 * @date 2020/8/5
 */
public interface CommentDao extends JpaRepository<Comment, Integer>, JpaSpecificationExecutor<Comment> {
}
