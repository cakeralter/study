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
 * 角色资源表
 *
 * @author cakeralter
 * @since 2020-08-06
 */
@EqualsAndHashCode
@ToString
@Setter
@Getter
@TableName("um_role_resource")
@ApiModel(value = "RoleResource对象", description = "角色资源表")
public class RoleResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "角色ID")
    @TableField("role_id")
    private Integer roleId;

    @ApiModelProperty(value = "资源ID")
    @TableField("resource_id")
    private Integer resourceId;

    @TableField("create_time")
    private LocalDateTime createTime;
}
