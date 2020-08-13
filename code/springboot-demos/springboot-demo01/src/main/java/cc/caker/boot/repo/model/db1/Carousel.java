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
 * @author cakeralter
 * @date 2020/8/13
 */
@EqualsAndHashCode
@ToString
@Setter
@Getter
@TableName("cm_carousel")
@ApiModel(value = "Carousel对象", description = "轮播图表")
public class Carousel implements Serializable {

    private static final long serialVersionUID = -743934499214689104L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("轮播图图片地址")
    @TableField("url")
    private String url;

    @ApiModelProperty("点击后的跳转地址")
    @TableField("redirect_url")
    private String redirectUrl;

    @ApiModelProperty("轮播图状态")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("轮播图描述")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "创建时间", hidden = true)
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者id", hidden = true)
    @TableField("create_user_id")
    private Integer createUserId;

    @ApiModelProperty(value = "创建者昵称", hidden = true)
    @TableField("create_nick_name")
    private String createNickName;

    @ApiModelProperty("最后修改时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty("修改者id")
    @TableField("update_user_id")
    private Integer updateUserId;

    @ApiModelProperty("修改者昵称")
    @TableField("update_nick_name")
    private String updateNickName;

    @ApiModelProperty("排序值(字段越大越靠前)")
    @TableField("sort")
    private Integer sort;
}
