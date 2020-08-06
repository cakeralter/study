package cc.caker.springboot.controller;

import cc.caker.common.service.RedisService;
import cc.caker.common.vo.ResponseResult;
import cc.caker.springboot.repo.model.db1.User;
import cc.caker.springboot.service.UserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author cakeralter
 * @date 2020/7/23
 */
@RequiredArgsConstructor
@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final RedisService redisService;

    @ApiOperation("通过ID查询用户")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path", dataType = "Integer")
    )
    @PostMapping("/{id}")
    public ResponseResult<User> user(@PathVariable("id") Integer id) {
        return ResponseResult.ok(userService.getById(id));
    }

    @ApiOperation("分页查询所有用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", defaultValue = "1", dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "每页条数", defaultValue = "5", dataType = "Integer")
    })
    @PostMapping("/list")
    public ResponseResult<IPage<User>> list(@RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "5") Integer size) {
        return ResponseResult.ok(userService.page(new Page<>(page, size)));
    }

    @ApiOperation("查询所有用户")
    @PostMapping("/all")
    public ResponseResult<List<User>> all() {
        List<User> all = redisService.get("userAll", User.class);
        if (CollectionUtils.isEmpty(all)) {
            all = userService.list();
            redisService.put("userAll", all);
        }
        return ResponseResult.ok(all);
    }
}
