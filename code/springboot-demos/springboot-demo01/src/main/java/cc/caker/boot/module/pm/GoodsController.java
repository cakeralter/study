package cc.caker.boot.module.pm;

import cc.caker.boot.module.pm.service.GoodsService;
import cc.caker.boot.repo.model.db2.Goods;
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
@Api(tags = "商品管理")
@RestController
@RequestMapping("/pm/goods")
public class GoodsController {

    private final GoodsService goodsService;

    @ApiOperation("插入商品")
    @PostMapping("/s/save")
    public ResponseResult<?> save(Goods goods) {
        return goodsService.save(goods) ? ResponseResult.ok() : ResponseResult.fail();
    }

    @ApiOperation("删除商品")
    @DeleteMapping("/d/delete")
    public ResponseResult<Integer> delete(@RequestParam("ids") Integer[] ids) {
        return ResponseResult.ok();
    }

    @ApiOperation("更新商品")
    @PutMapping("/u/update")
    public ResponseResult<?> update(Goods goods) {
        return goodsService.updateById(goods) ? ResponseResult.ok() : ResponseResult.fail();
    }

    @ApiOperation("通过ID查询商品")
    @GetMapping("/q/{id}")
    public ResponseResult<Goods> user(@PathVariable("id") Integer id) {
        return ResponseResult.ok(goodsService.getById(id));
    }

    @ApiOperation("分页查询所有商品")
    @GetMapping("/q/list")
    public ResponseResult<IPage<Goods>> list(@RequestParam(defaultValue = "1") Integer page,
                                             @RequestParam(defaultValue = "5") Integer size) {
        return ResponseResult.ok(goodsService.page(new Page<>(page, size)));
    }

    @ApiOperation("查询所有商品")
    @GetMapping("/q/all")
    public ResponseResult<List<Goods>> all() {
        return ResponseResult.ok();
    }
}
