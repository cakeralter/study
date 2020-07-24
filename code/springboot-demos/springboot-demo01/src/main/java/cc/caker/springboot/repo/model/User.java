package cc.caker.springboot.repo.model;

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
public class User implements Serializable {

    private static final long serialVersionUID = -1246243257118300313L;
    private Integer id;
    private String username;
    private String password;
    private Integer age;
}
