package cc.caker.springboot.repo.model.db2;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author cakeralter
 * @since 2020-08-06
 */
@EqualsAndHashCode
@ToString
@Getter
@Setter
@TableName("t_log")
@ApiModel(value = "Log对象", description = "")
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;
    @ApiModelProperty(value = "类型：1表示系统信息，2表示日志内容信息")
    @TableField("TYPE")
    private String type;
    @ApiModelProperty(value = "系统类型-位数，当type=1时候启用")
    @TableField("SYSTEM")
    private String system;
    @ApiModelProperty(value = "ip，当type=1时候启用")
    @TableField("HOST")
    private String host;
    @ApiModelProperty(value = "总内存量，当type=1时候启用")
    @TableField("TOTAL_MEMORY")
    private String totalMemory;
    @ApiModelProperty(value = "空闲内存，当type=1时候启用")
    @TableField("FREE_MEMORY")
    private String freeMemory;
    @ApiModelProperty(value = "工作盘总容量，当type=1时候启用")
    @TableField("TOTAL_DISK")
    private String totalDisk;
    @ApiModelProperty(value = "工作盘剩余容量，当type=1时候启用")
    @TableField("FREE_DISK")
    private String freeDisk;
    @ApiModelProperty(value = "CPU可用百分比，当type=1时候启用")
    @TableField("CPU_CANUSE")
    private String cpuCanuse;
    @ApiModelProperty(value = "连接目标数据库结果，当type=1时候启用")
    @TableField("DB_CONNECT")
    private String dbConnect;
    @TableField("LOCATION")
    private String location;
    @TableField("LOG_LEVEL")
    private String logLevel;
    @TableField("MESSAGE")
    private String message;
}
