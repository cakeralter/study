package cc.caker.boot.repo.entity.db2;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author cakeralter
 * @date 2020/8/5
 */
@ToString
@Setter
@Getter
@Entity
@Table(name = "t_user")
public class User implements Serializable {

    private static final long serialVersionUID = -1453435234448368065L;
    @Id
    @Column(name = "id")
    @GenericGenerator(name = "uid", strategy = "org.hibernate.id.IncrementGenerator")
    @GeneratedValue(generator = "uid")
    private Integer id;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "userface")
    private String userFace;
    @Column(name = "state", columnDefinition = "tinyint(1)", nullable = false)
    private Integer state;
    @Column(name = "phone")
    private String phone;
    @Column(name = "qq")
    private String qq;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "remark")
    private String remark;
    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;
}
