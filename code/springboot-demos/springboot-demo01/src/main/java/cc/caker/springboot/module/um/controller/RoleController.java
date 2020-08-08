package cc.caker.springboot.module.um.controller;

import cc.caker.common.service.RedisService;
import cc.caker.common.vo.ResponseResult;
import cc.caker.springboot.constant.RedisConstant;
import cc.caker.springboot.module.um.service.RoleResourceService;
import cc.caker.springboot.module.um.service.RoleService;
import cc.caker.springboot.repo.model.db1.Resource;
import cc.caker.springboot.repo.model.db1.Role;
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
 * @date 2020/8/7
 */
@RequiredArgsConstructor
@Api(tags = "角色管理接口")
@RestController
@RequestMapping("/um/role")
public class RoleController {

    private final RedisService redisService;
    private final RoleService roleService;
    private final RoleResourceService roleResourceService;

    @ApiOperation("插入角色")
    @PostMapping("/s/save")
    public ResponseResult<?> save(Role role) {
        return roleService.save(role) ? ResponseResult.ok() : ResponseResult.fail();
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/d/delete")
    public ResponseResult<Integer> delete(@RequestParam("ids") Integer[] ids) {
        return ResponseResult.ok(roleService.delete(ids));
    }

    @ApiOperation("更新角色")
    @PatchMapping("/u/update")
    public ResponseResult<?> update(Role role) {
        return roleService.updateById(role) ? ResponseResult.ok() : ResponseResult.fail();
    }

    @ApiOperation("通过ID查询角色")
    @PostMapping("/q/{id}")
    public ResponseResult<Role> user(@PathVariable("id") Integer id) {
        return ResponseResult.ok(roleService.getById(id));
    }

    @ApiOperation("分页查询所有角色")
    @PostMapping("/q/list")
    public ResponseResult<IPage<Role>> list(@RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "5") Integer size) {
        return ResponseResult.ok(roleService.page(new Page<>(page, size)));
    }

    @ApiOperation("查询所有角色")
    @PostMapping("/q/all")
    public ResponseResult<List<Role>> all() {
        return ResponseResult.ok(roleService.list());
    }

    @ApiOperation("授予角色资源")
    @PostMapping("/g/{roleId}/resource")
    public ResponseResult<?> grant(@PathVariable Integer roleId, @RequestParam("resourceIds[]") Integer[] resourceIds) {
        return roleResourceService.grantResource(roleId, resourceIds) ? ResponseResult.ok() : ResponseResult.fail();
    }

    @ApiOperation("查询角色所有资源")
    @PostMapping("/q/{roleId}/resources")
    public ResponseResult<List<Resource>> resources(@PathVariable("roleId") Integer roleId) {
        String key = RedisConstant.UM_ROLE_RESOURCE + "::" + roleId;
        List<Resource> resources = redisService.get(key, Resource.class);
        if (CollectionUtils.isEmpty(resources)) {
            resources = roleResourceService.getResourcesByRoleId(roleId);
            redisService.put(key, resources);
        }
        return ResponseResult.ok(resources);
    }
}
