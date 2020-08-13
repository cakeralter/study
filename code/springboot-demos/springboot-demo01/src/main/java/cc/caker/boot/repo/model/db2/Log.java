package cc.caker.boot.repo.model.db2;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author cakeralter
 * @since 2020-08-06
 */
@Builder
@EqualsAndHashCode
@ToString
@Getter
@Setter
@TableName("um_log")
@ApiModel(value = "Log对象", description = "日志表")
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "日志信息描述", hidden = true)
    @TableField("description")
    private String description;

    @ApiModelProperty("方法名称")
    @TableField("method")
    private String method;

    @ApiModelProperty("日志类型")
    @TableField("type")
    private Integer type;

    @ApiModelProperty("请求的ip")
    @TableField("request_ip")
    private String requestIp;

    @ApiModelProperty("异常错误码")
    @TableField("exception_code")
    private String exceptionCode;

    @ApiModelProperty("异常详情")
    @TableField("exception_detail")
    private String exceptionDetail;

    @ApiModelProperty("请求参数")
    @TableField("params")
    private String params;

    @ApiModelProperty("请求的用户id")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;
}
