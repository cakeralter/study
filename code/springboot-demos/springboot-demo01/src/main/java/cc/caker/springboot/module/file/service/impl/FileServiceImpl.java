package cc.caker.springboot.module.file.service.impl;

import cc.caker.springboot.module.file.service.FileService;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

import static cc.caker.springboot.constant.FileConst.FILE_UPLOAD_DIRECTORY;

/**
 * @author cakeralter
 * @date 2020/8/9
 */
@Service
public class FileServiceImpl implements FileService {

    @Override
    public int upload(MultipartFile... files) {
        if (Objects.isNull(files)) {
            return 0;
        }
        for (MultipartFile file : files) {
            File f = new File(FILE_UPLOAD_DIRECTORY + UUID.randomUUID());
            try {
                FileUtils.writeByteArrayToFile(f, file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return 1;
    }
}
