package cc.caker.boot.componet;

import cc.caker.common.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cakeralter
 * @date 2020/9/1
 */
@Slf4j
@RestController
@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseResult<String> handle(Exception e) {
        log.error("请求出错：[{}]", e.getMessage());
        return ResponseResult.fail(e.getMessage());
    }
}
