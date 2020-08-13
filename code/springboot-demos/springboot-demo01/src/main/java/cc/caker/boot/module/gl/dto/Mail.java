package cc.caker.boot.module.gl.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

/**
 * @author cakeralter
 * @date 2020/8/11
 */
@ToString
@Setter
@Getter
public class Mail implements Serializable {

    private final static long serialVersionUID = 1L;
    /**
     * 标题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String text;
    /**
     * 发送地址
     */
    private String[] to;
    /**
     * 抄送
     */
    private String[] cc;
    /**
     * 选用模板名
     */
    private String templateName;
    /**
     * 模板数据
     */
    private Map<String, Object> templateModel;
}
