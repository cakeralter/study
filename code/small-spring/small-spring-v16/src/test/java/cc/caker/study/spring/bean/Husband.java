package cc.caker.study.spring.bean;

import lombok.Data;

/**
 * Husband
 *
 * @author cakeralter
 * @date 2022/5/15
 * @since 1.0
 */
@Data
public class Husband {

    private Wife wife;

    public String queryWife() {
        return "Husband.wife";
    }
}
