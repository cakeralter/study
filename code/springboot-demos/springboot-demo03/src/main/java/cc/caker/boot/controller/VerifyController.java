package cc.caker.boot.controller;

import cc.caker.boot.service.impl.UserServiceImpl;
import cc.caker.common.vo.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author cakeralter
 * @date 2020/9/1
 */
@RequiredArgsConstructor
@RestController
public class VerifyController {

    private final UserServiceImpl userService;

    /**
     * 获取用户抢购key
     *
     * @param uid
     * @param sid
     * @return
     */
    @GetMapping("/hash")
    public ResponseResult<String> hash(Long uid, Long sid) {
        if (Objects.isNull(uid) || Objects.isNull(sid)) {
            throw new RuntimeException("用户或商品信息有误");
        }
        return ResponseResult.ok(userService.getHash(uid, sid));
    }
}
