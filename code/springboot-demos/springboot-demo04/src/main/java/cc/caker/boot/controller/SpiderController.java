package cc.caker.boot.controller;

import cc.caker.boot.service.BangumiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangyl
 * @date 2019/6/20
 * @description
 */
@Api(tags = "爬虫api")
@RestController
@RequestMapping("/spider")
public class SpiderController {

    @Autowired
    private BangumiService bangumiService;

    @ApiOperation("获取bangumi季番")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year", value = "年份", required = true, dataType = "int"),
            @ApiImplicitParam(name = "month", value = "月份", required = true, dataType = "int", allowableValues = "[1, 4, 7, 10]")
    })
    @GetMapping("/bangumi/{year}/{month}")
    public void bangumiSeasonAnime(@PathVariable("year") int year, @PathVariable("month") int month) throws Exception {
        bangumiService.getSeasonAnime(year, month);
    }

    @ApiOperation("获取2000年至今所有番剧列表")
    @GetMapping("/bangumi/all")
    public void bangumiSeasonAnimes() throws Exception {
        bangumiService.getSeasonAnimes();
    }
}
