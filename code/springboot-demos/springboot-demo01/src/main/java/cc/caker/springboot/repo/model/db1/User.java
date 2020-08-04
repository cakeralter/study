package cc.caker.springboot.repo.model.db1;

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
 * @date 2020/7/23
 */
@Setter
@Getter
@ToString
@TableName("t_user")
@ApiModel(value = "User对象", description = "用户表")
public class User implements Serializable {

    private static final long serialVersionUID = -1246243257118300313L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField
    private String username;
    @TableField
    private String password;
    @TableField
    private Integer age;
}
