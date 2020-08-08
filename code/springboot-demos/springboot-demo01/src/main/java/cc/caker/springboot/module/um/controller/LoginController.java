package cc.caker.springboot.module.um.controller;

import cc.caker.common.vo.ResponseResult;
import cc.caker.springboot.module.um.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
@RequestMapping("/um/login")
public class LoginController {

    private final LoginService loginService;

    /**
     * 返回token
     *
     * @param username
     * @param password
     * @return
     */
    @ApiOperation("登录")
    @PostMapping
    public ResponseResult<?> login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken(username, password));
        return ResponseResult.ok(subject.getPrincipal());
    }
}
