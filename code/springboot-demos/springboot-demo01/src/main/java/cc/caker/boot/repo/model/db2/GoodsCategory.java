package cc.caker.boot.repo.model.db2;

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
 * @date 2020/8/13
 */
@EqualsAndHashCode
@ToString
@Setter
@Getter
@TableName("pm_goods_category")
@ApiModel(value = "GoodsCategory对象", description = "商品分类表")
public class GoodsCategory implements Serializable {

    private static final long serialVersionUID = -8301498360419414237L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("分类名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("父分类id")
    @TableField("parent_id")
    private Integer parentId;

    @ApiModelProperty("分类类型")
    @TableField("type")
    private Integer type;

    @ApiModelProperty("删除标识字段")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "创建时间", hidden = true)
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者id", hidden = true)
    @TableField("create_user_id")
    private Integer createUserId;

    @ApiModelProperty(value = "创建者昵称", hidden = true)
    @TableField("create_nick_name")
    private Integer createNickName;

    @ApiModelProperty("修改时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty("修改者id")
    @TableField("update_user_id")
    private Integer updateUserId;

    @ApiModelProperty("修改者昵称")
    @TableField("update_nick_name")
    private Integer updateNickName;

    @ApiModelProperty("排序值")
    @TableField("sort")
    private Integer sort;
}
