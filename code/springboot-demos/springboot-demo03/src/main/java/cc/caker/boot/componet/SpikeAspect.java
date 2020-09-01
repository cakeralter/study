package cc.caker.boot.componet;

import cc.caker.boot.repo.entity.Order;
import cc.caker.common.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author cakeralter
 * @date 2020/9/1
 */
@Slf4j
@Component
@Aspect
public class SpikeAspect {

    @Pointcut("execution(* cc.caker.boot.controller.SpikeController.*(..)) " +
            "|| execution(* cc.caker.boot.controller.SpikeUpController.*(..))")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) {
        try {
            Optional<Long> optional = Optional.ofNullable(point)
                    .map(ProceedingJoinPoint::getArgs)
                    .map(x -> x[0])
                    .map(Object::toString)
                    .map(Long::valueOf);
            if (!optional.isPresent()) {
                throw new RuntimeException("商品有误");
            }
            Object result = point.proceed();
            @SuppressWarnings("unchecked")
            ResponseResult<Order> rr = (ResponseResult<Order>) result;
            log.info("成功购买物品：[{}]，订单号为：[{}]", rr.getData().getName(), rr.getData().getId());
            return result;
        } catch (Throwable e) {
            log.error("购买失败：[{}]", e.getMessage());
            return ResponseResult.fail(e.getMessage());
        }
    }
}
