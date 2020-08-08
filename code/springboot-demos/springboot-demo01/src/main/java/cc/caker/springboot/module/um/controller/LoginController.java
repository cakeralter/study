package cc.caker.springboot.module.um.controller;

import cc.caker.common.vo.ResponseResult;
import cc.caker.springboot.module.um.service.LoginService;
import cc.caker.springboot.repo.model.db1.Admin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.attribute.UserPrincipalNotFoundException;

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
     * @param admin
     * @return
     * @throws UserPrincipalNotFoundException
     */
    @ApiOperation("登录")
    @PostMapping
    public ResponseResult<String> login(@RequestBody Admin admin) {
        return loginService.login(admin);
    }
}
