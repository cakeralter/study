package cc.caker.boot.module.gl.service.impl;

import cc.caker.boot.component.CustomProperties;
import cc.caker.boot.module.gl.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @author cakeralter
 * @date 2020/8/9
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class FileServiceImpl implements FileService {

    private final CustomProperties properties;

    @Override
    public int upload(MultipartFile... files) {
        if (Objects.isNull(files)) {
            return 0;
        }
        // 创建文件夹
//        File dir = new File(properties.getUploadPath() + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        File dir = new File(properties.getUploadPath());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        int count = 0;
        for (MultipartFile file : files) {
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
            try {
                String fileName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")) + suffix;
                File newFile = new File(dir, fileName);
                FileUtils.writeByteArrayToFile(newFile, file.getBytes());
                count++;
            } catch (IOException e) {
                log.error("文件上传出错：{}", e.getMessage());
            }
        }
        return count;
    }
}
