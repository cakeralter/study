package cc.caker.boot.repo.model.db1;

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
 * 用户角色表
 *
 * @author cakeralter
 * @since 2020-08-06
 */
@EqualsAndHashCode
@ToString
@Setter
@Getter
@TableName("um_admin_role")
@ApiModel(value = "AdminRole对象", description = "用户角色表")
public class AdminRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    @TableField("admin_id")
    private Integer adminId;

    @ApiModelProperty(value = "角色ID")
    @TableField("role_id")
    private Integer roleId;

    @TableField("create_time")
    private LocalDateTime createTime;
}
