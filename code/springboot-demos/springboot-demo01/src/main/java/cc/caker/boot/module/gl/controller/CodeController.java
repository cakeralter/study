package cc.caker.boot.module.gl.controller;

import cc.caker.boot.component.limiter.RequestLimiter;
import cc.caker.boot.component.log.SysLog;
import cc.caker.boot.constant.Enumerations.OperationType;
import cc.caker.boot.module.gl.service.CodeService;
import cc.caker.boot.module.gl.service.LoginService;
import cc.caker.common.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cakeralter
 * @date 2020/8/13
 */
@RequiredArgsConstructor
@Api(tags = "验证码相关接口")
@RequestMapping("/code")
@RestController
public class CodeController {

    private final CodeService codeService;
    private final LoginService loginService;

    @RequestLimiter(qps = 0.1d, message = "操作太过频繁, 请稍后再试!")
    @SysLog
    @ApiOperation("发送注册验证码")
    @PostMapping("/register")
    public ResponseResult<String> register(String username, String email) {
        return codeService.send(username, email, OperationType.REGISTER)
                ? ResponseResult.ok() : ResponseResult.fail();
    }
}
