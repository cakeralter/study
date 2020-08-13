package cc.caker.boot.module.sm.controller;

import cc.caker.boot.component.limiter.RequestLimiter;
import cc.caker.boot.module.sm.service.LogService;
import cc.caker.boot.repo.model.db2.Log;
import cc.caker.common.vo.ResponseResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author cakeralter
 * @date 2020/8/4
 */
@RequiredArgsConstructor
@Api(tags = "日志管理接口")
@RestController
@RequestMapping("/sm/log")
public class LogController {

    private final LogService logService;

    @ApiOperation("通过ID查询日志")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id", value = "日志ID", required = true, paramType = "path", dataType = "Integer")
    )
    @PostMapping("/q/{id}")
    public ResponseResult<Log> user(@PathVariable("id") Integer id) {
        return ResponseResult.ok(logService.getById(id));
    }

    @RequestLimiter(qps = 1d)
    @ApiOperation("分页查询所有日志")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", defaultValue = "1", dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "每页条数", defaultValue = "5", dataType = "Integer")
    })
    @PostMapping("/q/list")
    public ResponseResult<IPage<Log>> list(@RequestParam(defaultValue = "1") Integer page,
                                           @RequestParam(defaultValue = "5") Integer size) {
        return ResponseResult.ok(logService.page(new Page<>(page, size)));
    }

    @ApiOperation("XSS测试")
    @PostMapping("/t/xss")
    public ResponseResult<String> testXss(String str) {
        return ResponseResult.ok(str);
    }
}
