package cc.caker.boot.module.cm;

import cc.caker.boot.module.cm.service.CarouselService;
import cc.caker.boot.repo.model.db1.Carousel;
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
@Api(tags = "轮播图管理")
@RestController
@RequestMapping("/cm/carousel")
public class CarouselController {

    private final CarouselService carouselService;

    @ApiOperation("插入轮播图")
    @PostMapping("/s/save")
    public ResponseResult<?> save(Carousel carousel) {
        return carouselService.save(carousel) ? ResponseResult.ok() : ResponseResult.fail();
    }

    @ApiOperation("删除轮播图")
    @DeleteMapping("/d/delete")
    public ResponseResult<Integer> delete(@RequestParam("ids") Integer[] ids) {
        return ResponseResult.ok();
    }

    @ApiOperation("更新轮播图")
    @PutMapping("/u/update")
    public ResponseResult<?> update(Carousel carousel) {
        return carouselService.updateById(carousel) ? ResponseResult.ok() : ResponseResult.fail();
    }

    @ApiOperation("通过ID查询轮播图")
    @GetMapping("/q/{id}")
    public ResponseResult<Carousel> user(@PathVariable("id") Integer id) {
        return ResponseResult.ok(carouselService.getById(id));
    }

    @ApiOperation("分页查询所有轮播图")
    @GetMapping("/q/list")
    public ResponseResult<IPage<Carousel>> list(@RequestParam(defaultValue = "1") Integer page,
                                                @RequestParam(defaultValue = "5") Integer size) {
        return ResponseResult.ok(carouselService.page(new Page<>(page, size)));
    }

    @ApiOperation("查询所有轮播图")
    @GetMapping("/q/all")
    public ResponseResult<List<Carousel>> all() {
        return ResponseResult.ok();
    }
}
