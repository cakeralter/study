package cc.caker.springboot.repo.model.db1;

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
 * @date 2020/8/6
 */
@EqualsAndHashCode
@ToString
@Getter
@Setter
@TableName("t_admin_resource")
@ApiModel(value = "AdminResource对象", description = "用户资源表")
public class AdminResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    @TableField("admin_id")
    private Integer adminId;

    @ApiModelProperty(value = "资源ID")
    @TableField("resource_id")
    private Integer resourceId;

    @TableField("create_time")
    private LocalDateTime createTime;
}
