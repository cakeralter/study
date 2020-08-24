package cc.caker.boot.repo.dao;

import cc.caker.boot.repo.po.Anime;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author wangyl
 * @date 2019/6/20
 * @description
 */
public interface AnimeDao extends MongoRepository<Anime, Integer> {
}
