package cc.caker.boot.repo.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;


/**
 * 库存
 *
 * @author 90344
 */
@ToString
@EqualsAndHashCode
@Setter
@Getter
public class Stock implements Serializable {

    private static final long serialVersionUID = 565273511255351103L;
    private Long id;
    private String name;
    /**
     * 总库存
     */
    private Long count;
    /**
     * 售出
     */
    private Long sale;
    /**
     * 版本号
     */
    private Integer version;
}
