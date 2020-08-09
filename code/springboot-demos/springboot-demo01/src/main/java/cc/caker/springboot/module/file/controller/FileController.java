package cc.caker.springboot.module.file.controller;

import cc.caker.common.vo.ResponseResult;
import cc.caker.springboot.component.log.SysLog;
import cc.caker.springboot.module.file.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author cakeralter
 * @date 2020/8/9
 */
@RequiredArgsConstructor
@Api(tags = "文件相关接口")
@RestController
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    @SysLog
    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public ResponseResult<Integer> upload(@RequestParam("files[]") MultipartFile[] files) {
        return ResponseResult.ok(fileService.upload(files));
    }
}
