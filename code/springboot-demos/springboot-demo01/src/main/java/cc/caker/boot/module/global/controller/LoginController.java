package cc.caker.boot.module.global.controller;

import cc.caker.common.enumeration.ResponseCode;
import cc.caker.common.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cakeralter
 * @date 2020/8/8
 */
@RequiredArgsConstructor
@Api(tags = "用户登录接口")
@RestController
public class LoginController {

    @ApiOperation("登录")
    @PostMapping("/login")
    public ResponseResult<?> login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        SecurityUtils.getSubject().login(new UsernamePasswordToken(username, password));
        return ResponseResult.ok(subject.getSession().getId());
    }

    @ApiOperation("退出登录")
    @GetMapping("/logout")
    public ResponseResult<String> logout() {
        SecurityUtils.getSubject().logout();
        return ResponseResult.ok();
    }

    @ApiOperation("查询已登录用户")
    @PostMapping("/me")
    public ResponseResult<?> me() {
        return ResponseResult.ok(SecurityUtils.getSubject().getPrincipal());
    }

    @ApiOperation(value = "未登录", hidden = true)
    @RequestMapping("/unlogin")
    public ResponseResult<String> notLogin() {
        return ResponseResult.fail(ResponseCode.FORBIDDEN.getStatus(), "请先登录系统!");
    }

    @ApiOperation(value = "未授权", hidden = true)
    @RequestMapping("/unauthorization")
    public ResponseResult<String> unAuthorization() {
        return ResponseResult.fail(ResponseCode.UNAUTHORIZED.getStatus(), "账号无权访问!");
    }
}
