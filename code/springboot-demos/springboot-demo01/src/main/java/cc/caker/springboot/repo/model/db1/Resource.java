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
 * 后台资源表
 *
 * @author cakeralter
 * @since 2020-08-06
 */
@EqualsAndHashCode
@ToString
@Setter
@Getter
@TableName("t_resource")
@ApiModel(value = "Resource对象", description = "后台资源表")
public class Resource implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "父级资源id")
    @TableField("parent_id")
    private Integer parentId;
    @ApiModelProperty(value = "资源名称")
    @TableField("name")
    private String name;
    @ApiModelProperty(value = "资源编码")
    @TableField("code")
    private String code;
    @ApiModelProperty(value = "图标")
    @TableField("icon")
    private String icon;
    @ApiModelProperty(value = "资源类型：1->目录；2->菜单；3->按钮（接口绑定权限）")
    @TableField("type")
    private Integer type;
    @ApiModelProperty(value = "前端资源路径")
    @TableField("uri")
    private String uri;
    @ApiModelProperty(value = "启用状态；0->禁用；1->启用")
    @TableField("status")
    private Integer status;
    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;
    @ApiModelProperty(value = "最后修改时间")
    @TableField("update_time")
    private LocalDateTime updateTime;
    @ApiModelProperty(value = "排序")
    @TableField("sort")
    private Integer sort;
}
