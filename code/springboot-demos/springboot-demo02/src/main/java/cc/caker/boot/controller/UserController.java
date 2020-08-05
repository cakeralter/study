package cc.caker.boot.controller;

import cc.caker.boot.repo.entity.db2.User;
import cc.caker.boot.service.UserService;
import cc.caker.common.boot.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @author cakeralter
 * @date 2020/8/4
 */
@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("通过ID查询用户")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path", dataType = "Integer")
    )
    @PostMapping("/{id}")
    public ResponseResult<User> user(@PathVariable("id") Integer id) {
        return ResponseResult.ok(userService.user(id));
    }

    @ApiOperation("分页查询所有用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", defaultValue = "1", dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "每页条数", defaultValue = "5", dataType = "Integer")
    })
    @PostMapping("/list")
    public ResponseResult<Page<User>> list(@RequestParam(defaultValue = "1") Integer page,
                                           @RequestParam(defaultValue = "5") Integer size) {
        return ResponseResult.ok(userService.page(page, size));
    }
}
