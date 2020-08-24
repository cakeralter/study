package cc.caker.boot.service;

/**
 * @author wangyl
 * @date 2019/6/20
 * @description
 */
public interface BangumiService {

    void getSeasonAnime(int year, int month) throws Exception;

    void getSeasonAnimes() throws Exception;
}
