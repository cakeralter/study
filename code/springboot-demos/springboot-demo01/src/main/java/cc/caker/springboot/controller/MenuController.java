package cc.caker.springboot.controller;

import cc.caker.common.boot.vo.ResponseResult;
import cc.caker.springboot.repo.model.db2.Menu;
import cc.caker.springboot.service.MenuService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author cakeralter
 * @date 2020/8/4
 */
@Api(tags = "菜单管理接口")
@RestController
@RequestMapping("/meun")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation("通过ID查询菜单")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id", value = "菜单ID", required = true, paramType = "path", dataType = "Integer")
    )
    @PostMapping("/{id}")
    public ResponseResult<Menu> user(@PathVariable("id") Integer id) {
        return ResponseResult.ok(menuService.getById(id));
    }

    @ApiOperation("分页查询所有菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", defaultValue = "1", dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "每页条数", defaultValue = "5", dataType = "Integer")
    })
    @PostMapping("/list")
    public ResponseResult<IPage<Menu>> list(@RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "5") Integer size) {
        return ResponseResult.ok(menuService.page(new Page<>(page, size)));
    }
}
