package cc.caker.boot.module.pm;

import cc.caker.boot.module.pm.service.GoodsCategoryService;
import cc.caker.boot.repo.model.db2.GoodsCategory;
import cc.caker.common.vo.ResponseResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author cakeralter
 * @date 2020/8/13
 */
@RequiredArgsConstructor
@Api(tags = "商品分类管理")
@RestController
@RequestMapping("/pm/category")
public class GoodsCategoryController {

    private final GoodsCategoryService categoryService;

    @ApiOperation("插入商品分类")
    @PostMapping("/s/save")
    public ResponseResult<?> save(GoodsCategory category) {
        return categoryService.save(category) ? ResponseResult.ok() : ResponseResult.fail();
    }

    @ApiOperation("删除商品分类")
    @DeleteMapping("/d/delete")
    public ResponseResult<Integer> delete(@RequestParam("ids") Integer[] ids) {
        return ResponseResult.ok();
    }

    @ApiOperation("更新商品分类")
    @PutMapping("/u/update")
    public ResponseResult<?> update(GoodsCategory category) {
        return categoryService.updateById(category) ? ResponseResult.ok() : ResponseResult.fail();
    }

    @ApiOperation("通过ID查询商品分类")
    @GetMapping("/q/{id}")
    public ResponseResult<GoodsCategory> user(@PathVariable("id") Integer id) {
        return ResponseResult.ok(categoryService.getById(id));
    }

    @ApiOperation("分页查询所有商品分类")
    @GetMapping("/q/list")
    public ResponseResult<IPage<GoodsCategory>> list(@RequestParam(defaultValue = "1") Integer page,
                                                     @RequestParam(defaultValue = "5") Integer size) {
        return ResponseResult.ok(categoryService.page(new Page<>(page, size)));
    }

    @ApiOperation("查询所有商品分类")
    @GetMapping("/q/all")
    public ResponseResult<List<GoodsCategory>> all() {
        return ResponseResult.ok();
    }
}
