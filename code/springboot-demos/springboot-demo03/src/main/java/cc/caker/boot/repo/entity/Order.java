package cc.caker.boot.repo.entity;


import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 订单
 *
 * @author 90344
 */
@Builder
@ToString
@EqualsAndHashCode
@Setter
@Getter
public class Order implements Serializable {

    private static final long serialVersionUID = 935384810762334068L;
    private Long id;
    private Long sid;
    private Long userId;
    private String name;
    private LocalDateTime createTime;
}
