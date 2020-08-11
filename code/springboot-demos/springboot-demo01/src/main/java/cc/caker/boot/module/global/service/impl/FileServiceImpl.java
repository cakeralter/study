package cc.caker.boot.module.global.service.impl;

import cc.caker.boot.module.global.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

import static cc.caker.boot.constant.FileConst.FILE_UPLOAD_DIRECTORY;

/**
 * @author cakeralter
 * @date 2020/8/9
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Override
    public int upload(MultipartFile... files) {
        if (Objects.isNull(files)) {
            return 0;
        }
        // 创建文件夹
        File dir = new File(FILE_UPLOAD_DIRECTORY + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "\\");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        int count = 0;
        for (MultipartFile file : files) {
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
            try {
                File newFile = new File(dir, UUID.randomUUID() + suffix);
                FileUtils.writeByteArrayToFile(newFile, file.getBytes());
                count++;
            } catch (IOException e) {
                log.error("文件上传出错：{}", e.getMessage());
            }
        }
        return count;
    }
}
