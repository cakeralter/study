package cc.caker.springboot.controller;

import cc.caker.common.service.RedisService;
import cc.caker.common.vo.ResponseResult;
import cc.caker.springboot.repo.model.db1.Admin;
import cc.caker.springboot.repo.model.db1.Role;
import cc.caker.springboot.service.AdminRoleService;
import cc.caker.springboot.service.AdminService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
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
@RequestMapping("/admin")
public class AdminController {

    private final RedisService redisService;
    private final AdminService adminService;
    private final AdminRoleService adminRoleService;

    @ApiOperation("插入用户")
    @PostMapping("/save")
    public ResponseResult<?> save(Admin admin) {
        return adminService.save(admin) ? ResponseResult.ok() : ResponseResult.fail();
    }

    @ApiOperation("更新用户")
    @PatchMapping("/update")
    public ResponseResult<?> update(Admin admin) {
        return adminService.updateById(admin) ? ResponseResult.ok() : ResponseResult.fail();
    }

    @ApiOperation("通过ID查询用户")
    @PostMapping("/{id}")
    public ResponseResult<Admin> user(@PathVariable("id") Integer id) {
        return ResponseResult.ok(adminService.getById(id));
    }

    @ApiOperation("分页查询所有用户")
    @PostMapping("/list")
    public ResponseResult<IPage<Admin>> list(@RequestParam(defaultValue = "1") Integer page,
                                             @RequestParam(defaultValue = "5") Integer size) {
        return ResponseResult.ok(adminService.page(new Page<>(page, size)));
    }

    @ApiOperation("查询所有用户")
    @PostMapping("/all")
    public ResponseResult<List<Admin>> all() {
        List<Admin> all = redisService.get("userAll", Admin.class);
        if (CollectionUtils.isEmpty(all)) {
            all = adminService.list();
            redisService.put("userAll", all);
        }
        return ResponseResult.ok(all);
    }

    @ApiOperation("查询用户所有角色")
    @PostMapping("/{adminId}/roles")
    public ResponseResult<List<Role>> roles(@PathVariable("adminId") Integer adminId) {
        return ResponseResult.ok(adminRoleService.getRolesByAdmin(adminId));
    }

    @ApiOperation("XSS测试")
    @PostMapping("/xss")
    public ResponseResult<String> testXss(String str) {
        return ResponseResult.ok(str);
    }
}
