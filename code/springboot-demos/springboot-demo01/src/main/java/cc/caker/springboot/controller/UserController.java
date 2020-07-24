package cc.caker.springboot.controller;

import cc.caker.springboot.common.vo.ResponseResult;
import cc.caker.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author cakeralter
 * @date 2020/7/23
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello SpringBoot!";
    }

    @PostMapping("/{id}")
    public ResponseResult<?> user(@PathVariable("id") Integer id) {
        return ResponseResult.ok(userService.selectById(id));
    }
}
