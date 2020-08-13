package cc.caker.boot.repo.model.db1;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * 用户表
 *
 * @author cakeralter
 * @since 2020-08-06
 */
@EqualsAndHashCode
@ToString
@Getter
@Setter
@TableName("um_admin")
@ApiModel(value = "Admin对象", description = "用户表")
@JsonIgnoreProperties(value = {"password", "secret"}, allowSetters = true)
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户名，用于显示")
    @TableField("nick_name")
    private String nickName;

    @ApiModelProperty("用户名，用于登录")
    @NotEmpty(message = "用户名不能为空")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "登录密码")
    @NotEmpty(message = "密码不能为空")
    @TableField(value = "password")
    private String password;

    @ApiModelProperty("头像")
    @TableField("icon")
    private String icon;

    @ApiModelProperty("邮箱")
    @Email(message = "邮箱格式不正确")
    @TableField("email")
    private String email;

    @ApiModelProperty("备注信息")
    @TableField("note")
    private String note;

    @ApiModelProperty(value = "创建时间", hidden = true)
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "最后登录时间")
    @TableField("login_time")
    private LocalDateTime loginTime;

    @ApiModelProperty("帐号启用状态：0->禁用；1->启用")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("用户私钥")
    @TableField("secret")
    private String secret;

    @ApiModelProperty("排序")
    @TableField("sort")
    private Integer sort;

    @TableField(exist = false)
    private Set<String> roles;

    @TableField(exist = false)
    private Set<String> perms;
}
