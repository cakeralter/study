package cc.caker.boot.service.impl;

import cc.caker.boot.repo.dao.AnimeDao;
import cc.caker.boot.repo.po.Anime;
import cc.caker.boot.service.BangumiService;
import cc.caker.boot.util.Const;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author wangyl
 * @date 2019/6/20
 * @description
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BangumiServiceImpl implements BangumiService {

    private final static String PREFIX = "/api/v2/bangumi";
    private final static int SEASON = 3;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AnimeDao animeDao;
    @Autowired
    private HttpServletResponse response;

    /**
     * 获取指定季度番剧列表
     *
     * @param year
     * @param month
     */
    @Override
    public void getSeasonAnime(int year, int month) throws Exception {
        String url = Const.SITE + PREFIX + "/season/anime/" + year + "/" + month;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String response = restTemplate.getForObject(url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(response);
        JsonNode bangumiList = node.path("bangumiList");
        List<Anime> animes = Lists.newArrayList();
        for (int i = 0; i < bangumiList.size(); i++) {
            JsonNode list = bangumiList.get(i);
            animes.add(mapper.readValue(list.toString(), Anime.class));
        }

        animeDao.saveAll(animes);
        System.out.println("finish spider " + year + "/" + month + "****************");
    }

    @Override
    public void getSeasonAnimes() throws Exception {
        LocalDateTime beg = LocalDateTime.of(2000, 1, 1, 0, 0);
        LocalDateTime end = LocalDateTime.now();

        for (LocalDateTime index = beg; index.isBefore(end); index = index.plusMonths(SEASON)) {
            getSeasonAnime(index.getYear(), index.getMonthValue());
        }

        response.getOutputStream().write("<h1>finish spider</h1>".getBytes());
    }
}
