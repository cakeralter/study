package cc.caker.springboot.module.um.controller;

import cc.caker.common.vo.ResponseResult;
import cc.caker.springboot.component.log.SysLog;
import cc.caker.springboot.module.um.service.ResourceService;
import cc.caker.springboot.repo.model.db1.Resource;
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
@Api(tags = "资源管理接口")
@RestController
@RequestMapping("/um/resource")
public class ResourceController {

    private final ResourceService resourceService;

    @ApiOperation("插入资源")
    @PostMapping("/s/save")
    public ResponseResult<?> save(Resource resource) {
        return resourceService.save(resource) ? ResponseResult.ok() : ResponseResult.fail();
    }

    @ApiOperation("删除资源")
    @DeleteMapping("/d/delete")
    public ResponseResult<Integer> delete(@RequestParam("ids") Integer[] ids) {
        return ResponseResult.ok(resourceService.delete(ids));
    }

    @ApiOperation("更新资源")
    @PatchMapping("/u/update")
    public ResponseResult<?> update(Resource resource) {
        return resourceService.updateById(resource) ? ResponseResult.ok() : ResponseResult.fail();
    }

    @ApiOperation("通过ID查询资源")
    @PostMapping("/q/{id}")
    public ResponseResult<Resource> user(@PathVariable("id") Integer id) {
        return ResponseResult.ok(resourceService.getById(id));
    }

    @ApiOperation("分页查询所有资源")
    @PostMapping("/q/list")
    public ResponseResult<IPage<Resource>> list(@RequestParam(defaultValue = "1") Integer page,
                                                @RequestParam(defaultValue = "5") Integer size) {
        return ResponseResult.ok(resourceService.page(new Page<>(page, size)));
    }

    @SysLog
    @ApiOperation("查询所有资源")
    @PostMapping("/q/all")
    public ResponseResult<List<Resource>> all() {
        return ResponseResult.ok(resourceService.findAll());
    }
}
