package cc.caker.springboot.config;

import cc.caker.common.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cakeralter
 * @date 2020/7/24
 */
@Slf4j
@RestController
@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseResult<?> handleException(Exception e) {
        log.error("请求出错：" + e.getMessage(), e);
        return ResponseResult.error(e.getMessage());
    }
}
