package cc.caker.boot.module.global.controller;

import cc.caker.boot.component.limiter.RequestLimiter;
import cc.caker.boot.component.log.SysLog;
import cc.caker.boot.module.um.service.AdminService;
import cc.caker.boot.repo.model.db1.Admin;
import cc.caker.common.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author cakeralter
 * @date 2020/8/12
 */
@RequiredArgsConstructor
@Api(tags = "用户注册接口")
@RestController
public class RegisterController {

    private final AdminService adminService;

    @RequestLimiter(qps = 0.5d)
    @SysLog
    @ApiOperation("用户注册")
    @PostMapping("/register")
    public ResponseResult<String> register(@Valid Admin admin, @RequestParam("code") String input) {
        return adminService.register(admin, input) ? ResponseResult.ok() : ResponseResult.fail();
    }
}
