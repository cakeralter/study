package cc.caker.boot.component.log;

import cc.caker.boot.constant.Enumerations;
import cc.caker.boot.repo.model.db1.Admin;
import cc.caker.boot.repo.model.db1.Log;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 系统日志切面
 *
 * @author cakeralter
 * @date 2020/8/9
 */
@RequiredArgsConstructor
@Slf4j
@Aspect
@Component
public class SysLogAspect {

    private final SysLogService logService;

    /**
     * 切点
     */
    @Pointcut("@annotation(cc.caker.boot.component.log.SysLog)")
    public void sysLogPointcut() {
    }

    /**
     * 前置通知
     *
     * @param joinPoint
     */
    @Before("sysLogPointcut()")
    public void doBefore(JoinPoint joinPoint) {
        Log.LogBuilder builder = Log.builder()
                .type(Enumerations.LogType.NORMAL.getValue());
        logService.add(initLog(joinPoint, builder));
    }

    /**
     * 异常通知
     *
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(value = "sysLogPointcut()", throwing = "ex")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        /*if(ex instanceof RuntimeException) {
        }*/
        Log.LogBuilder builder = Log.builder()
                .type(Enumerations.LogType.EXCEPTION.getValue())
                .exceptionCode(ex.getClass().getName())
                .exceptionDetail(ex.getMessage());
        logService.add(initLog(joinPoint, builder));
    }

    /**
     * 生成日志对象
     *
     * @param point
     * @param builder
     * @return
     */
    private Log initLog(JoinPoint point, Log.LogBuilder builder) {
        try {
            // 类
            Class<?> targetClass = point.getTarget().getClass();
            // 方法名
            builder.method(targetClass.toString() + "." + point.getSignature().getName());
            // 请求参数名+参数值的map
            // ip信息
            builder.requestIp("0.0.0.0");
            // 用户信息
            Admin principal = (Admin) SecurityUtils.getSubject().getPrincipal();
            builder.userId(principal.getId());
        } catch (Exception e) {
            log.error("日志记录出错：{}", e.getMessage());
        }

        return builder.build();
    }
}
