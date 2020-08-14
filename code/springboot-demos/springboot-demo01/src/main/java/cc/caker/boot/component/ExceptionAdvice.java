package cc.caker.boot.component;

import cc.caker.boot.component.log.SysLogService;
import cc.caker.boot.repo.model.db1.Log;
import cc.caker.common.enumeration.ResponseCode;
import cc.caker.common.vo.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ValidationException;

/**
 * 统一异常处理
 *
 * @author cakeralter
 * @date 2020/7/24
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@ControllerAdvice
public class ExceptionAdvice {

    private final SysLogService logService;

    /**
     * 参数验证有误
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseResult<String> handle(ValidationException e) {
        log.error("请求出错：" + e.getMessage(), e);
        logService.add(Log.byException(e, SecurityUtils.getSubject().getPrincipal()));
        return ResponseResult.fail(ResponseCode.UNAUTHORIZED.getStatus(), e.getMessage());
    }

    /**
     * 凭证有误
     *
     * @param e
     * @return
     */
    @ExceptionHandler(IncorrectCredentialsException.class)
    public ResponseResult<String> handle(IncorrectCredentialsException e) {
        log.error("请求出错：" + e.getMessage(), e);
        logService.add(Log.byException(e, SecurityUtils.getSubject().getPrincipal()));
        return ResponseResult.fail(ResponseCode.UNAUTHORIZED.getStatus(), "密码错误");
    }

    /**
     * 未认证
     *
     * @param e
     * @return
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseResult<String> handle(UnauthorizedException e) {
        log.error("请求出错：" + e.getMessage(), e);
        logService.add(Log.byException(e, SecurityUtils.getSubject().getPrincipal()));
        return ResponseResult.fail(ResponseCode.UNAUTHORIZED.getStatus(), e.getMessage());
    }

    /**
     * 未授权
     *
     * @param e
     * @return
     */
    @ExceptionHandler(UnauthenticatedException.class)
    public ResponseResult<String> handle(UnauthenticatedException e) {
        log.error("请求出错：" + e.getMessage(), e);
        logService.add(Log.byException(e, SecurityUtils.getSubject().getPrincipal()));
        return ResponseResult.fail(ResponseCode.FORBIDDEN.getStatus(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult<String> handle(Exception e) {
        log.error("请求出错：" + e.getMessage(), e);
        logService.add(Log.byException(e, SecurityUtils.getSubject().getPrincipal()));
        return ResponseResult.error(e.getMessage());
    }
}
