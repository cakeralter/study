package cc.caker.boot.vo;

import lombok.*;

import java.io.Serializable;

/**
 * @author cakeralter
 * @date 2020/9/2
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderMessage implements Serializable {

    private static long serialVersionUID = 7273887367163798002L;
    private Long uid;
    private Long sid;
    private String hash;
}
