package cc.caker.springboot.repo.model.db2;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author cakeralter
 * @date 2020/8/4
 */
@Setter
@Getter
@ToString
@TableName("t_menu")
@ApiModel(value = "Menu对象", description = "菜单表")
public class Menu implements Serializable {

    private static final long serialVersionUID = 4473791436468166444L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField
    private String name;
    @TableField
    private String url;
    @TableField
    private String path;
    @TableField
    private String component;
    @TableField
    private String iconCls;
    @TableField
    private Boolean keepAlive;
    @TableField
    private Boolean requireAuth;
    @TableField
    private Integer sort;
    @TableField
    private Integer parentId;
}
