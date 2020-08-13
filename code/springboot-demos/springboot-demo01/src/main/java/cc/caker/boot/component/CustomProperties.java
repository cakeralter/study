package cc.caker.boot.component;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author cakeralter
 * @date 2020/8/13
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties("custom")
public class CustomProperties {

    /**
     * 验证码长度
     */
    private int verifyCodeLength = 6;
    /**
     * 文件上传路径
     */
    private String uploadPath = "D:/upload/";

    @PostConstruct
    public void initFinished() {
        System.out.println(verifyCodeLength);
        System.out.println(uploadPath);
    }
}
