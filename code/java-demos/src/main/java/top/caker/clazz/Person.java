package top.caker.clazz;

import lombok.*;

import java.io.Serializable;

/**
 * @author cakeralter
 * @date 2020/8/18
 */
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person<T> implements Serializable {

    private static final long serialVersionUID = -3994784358153475783L;
    private Integer id;
    public String name;
    String sex;
    protected Integer age;
    private T desc;
}
