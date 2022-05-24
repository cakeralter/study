package cc.caker.study.mybatis.po;

import lombok.Data;

import java.util.Date;

/**
 * User
 *
 * @author cakeralter
 * @date 2022/5/24
 * @since 1.0
 */
@Data
public class User {

    private Long id;
    private String userId;          // 用户ID
    private String userHead;        // 头像
    private Date createTime;        // 创建时间
    private Date updateTime;        // 更新时间
}
