package cc.caker.boot.module.gl.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author cakeralter
 * @date 2020/8/9
 */
public interface FileService {

    /**
     * 文件上传
     *
     * @param files
     * @return
     */
    int upload(MultipartFile... files);
}
