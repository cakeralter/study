package cc.caker.springboot.module.um.controller;

import cc.caker.common.service.RedisService;
import cc.caker.common.vo.ResponseResult;
import cc.caker.springboot.constant.Constant;
import cc.caker.springboot.module.um.service.AdminResourceService;
import cc.caker.springboot.module.um.service.AdminRoleService;
import cc.caker.springboot.module.um.service.AdminService;
import cc.caker.springboot.repo.model.db1.Admin;
import cc.caker.springboot.repo.model.db1.Resource;
import cc.caker.springboot.repo.model.db1.Role;
import cc.caker.springboot.util.CodingUtils;
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
@RequestMapping("/um/admin")
public class AdminController {

    private final RedisService redisService;
    private final AdminService adminService;
    private final AdminRoleService adminRoleService;
    private final AdminResourceService adminResourceService;

    @ApiOperation("插入用户")
    @PostMapping("/save")
    public ResponseResult<?> save(Admin admin) {
        admin.setPassword(CodingUtils.encrypt(admin.getPassword(), admin.getSecret()));
        return adminService.save(admin) ? ResponseResult.ok() : ResponseResult.fail();
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/delete")
    public ResponseResult<Integer> delete(@RequestParam("ids") Integer[] ids) {
        return ResponseResult.ok(adminService.delete(ids));
    }

    @ApiOperation("更新用户")
    @PatchMapping("/update")
    public ResponseResult<?> update(Admin admin) {
        return adminService.updateById(admin) ? ResponseResult.ok() : ResponseResult.fail();
    }

    @ApiOperation("更新密码")
    @PatchMapping("/{adminId}/password")
    public ResponseResult<?> password(@PathVariable String adminId, String password) {
        return adminService.changePassword(adminId, password) ? ResponseResult.ok() : ResponseResult.fail();
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
        return ResponseResult.ok(adminService.list());
    }

    @ApiOperation("查询用户所有角色")
    @PostMapping("/{adminId}/roles")
    public ResponseResult<List<Role>> roles(@PathVariable("adminId") Integer adminId) {
        String key = Constant.UM_ADMIN_ROLE + "::" + adminId;
        List<Role> roles = redisService.get(key, Role.class);
        if (CollectionUtils.isEmpty(roles)) {
            roles = adminRoleService.getRolesByAdmin(adminId);
            redisService.put(key, roles);
        }
        return ResponseResult.ok(roles);
    }

    @ApiOperation("授予用户角色")
    @PostMapping("/{adminId}/grant/role")
    public ResponseResult<?> grantRole(@PathVariable("adminId") Integer adminId, @RequestParam("roleIds[]") Integer[] roleIds) {
        return adminRoleService.grantRole(adminId, roleIds) ? ResponseResult.ok() : ResponseResult.fail();
    }

    @ApiOperation("查询用户所有资源")
    @PostMapping("/{adminId}/resources")
    public ResponseResult<List<Resource>> resources(@PathVariable("adminId") Integer adminId) {
        String key = Constant.UM_ADMIN_RESOURCE + "::" + adminId;
        List<Resource> resources = redisService.get(key, Resource.class);
        if (CollectionUtils.isEmpty(resources)) {
            resources = adminResourceService.getResourceByAdminId(adminId);
            redisService.put(key, resources);
        }
        return ResponseResult.ok(resources);
    }

    @ApiOperation("授予用户资源")
    @PostMapping("/{adminId}/grant/resource")
    public ResponseResult<?> grant(@PathVariable("adminId") Integer adminId, @RequestParam("resourceIds[]") Integer[] resourceIds) {
        return adminResourceService.grantResource(adminId, resourceIds) ? ResponseResult.ok() : ResponseResult.fail();
    }
}
