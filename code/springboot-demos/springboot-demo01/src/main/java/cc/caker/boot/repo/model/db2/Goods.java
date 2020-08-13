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
@TableName("pm_goods")
@ApiModel(value = "Goods对象", description = "商品信息表")
public class Goods implements Serializable {

    private static final long serialVersionUID = -8176012398795407919L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("商品名")
    @TableField("name")
    private String name;

    @ApiModelProperty("商品简介")
    @TableField("description")
    private String description;

    @ApiModelProperty("关联分类id")
    @TableField("category_id")
    private Integer categoryId;

    @ApiModelProperty("商品主图")
    @TableField("cover_img")
    private String coverImg;

    @ApiModelProperty("商品轮播图")
    @TableField("carousel")
    private String carousel;

    @ApiModelProperty("商品详情")
    @TableField("detail")
    private String detail;

    @ApiModelProperty("商品标价(分)")
    @TableField("original_price")
    private Long originalPrice;

    @ApiModelProperty("商品实际售价(分)")
    @TableField("selling_price")
    private Long sellingPrice;

    @ApiModelProperty("商品库存数量")
    @TableField("stock_num")
    private Long stockNum;

    @ApiModelProperty("商品标签")
    @TableField("tag")
    private String tag;

    @ApiModelProperty("商品上架状态")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "添加者主键id", hidden = true)
    @TableField("create_user_id")
    private Integer createUserId;

    @ApiModelProperty(value = "添加者昵称", hidden = true)
    @TableField("create_nick_name")
    private String createNickName;

    @ApiModelProperty(value = "商品添加时间", hidden = true)
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("修改者主键id")
    @TableField("update_user_id")
    private Integer updateUserId;

    @ApiModelProperty("修改者昵称")
    @TableField("update_nick_name")
    private String updateNickName;

    @ApiModelProperty("商品最近修改时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty("排序")
    @TableField("sort")
    private Integer sort;
}
