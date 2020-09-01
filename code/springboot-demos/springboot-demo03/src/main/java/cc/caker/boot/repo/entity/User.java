package cc.caker.boot.repo.entity;

import lombok.*;

import java.io.Serializable;

/**
 * @author cakeralter
 * @date 2020/9/1
 */
@Builder
@ToString
@EqualsAndHashCode
@Setter
@Getter
public class User implements Serializable {

    private static final long serialVersionUID = -9039464800632083356L;
    private Long id;
    private String username;
}
