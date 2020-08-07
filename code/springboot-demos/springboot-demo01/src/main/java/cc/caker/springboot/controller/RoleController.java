package cc.caker.springboot.controller;

import cc.caker.common.vo.ResponseResult;
import cc.caker.springboot.repo.model.db1.Role;
import cc.caker.springboot.service.RoleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author cakeralter
 * @date 2020/8/7
 */
@RequiredArgsConstructor
@Api(tags = "角色管理接口")
@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    @ApiOperation("插入角色")
    @PostMapping("/save")
    public ResponseResult<?> save(Role role) {
        return roleService.save(role) ? ResponseResult.ok() : ResponseResult.fail();
    }

    @ApiOperation("更新角色")
    @PatchMapping("/update")
    public ResponseResult<?> update(Role role) {
        return roleService.updateById(role) ? ResponseResult.ok() : ResponseResult.fail();
    }

    @ApiOperation("通过ID查询角色")
    @PostMapping("/{id}")
    public ResponseResult<Role> user(@PathVariable("id") Integer id) {
        return ResponseResult.ok(roleService.getById(id));
    }

    @ApiOperation("分页查询所有角色")
    @PostMapping("/list")
    public ResponseResult<IPage<Role>> list(@RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "5") Integer size) {
        return ResponseResult.ok(roleService.page(new Page<>(page, size)));
    }

    @ApiOperation("查询所有角色")
    @PostMapping("/all")
    public ResponseResult<List<Role>> all() {
        return ResponseResult.ok(roleService.list());
    }
}
