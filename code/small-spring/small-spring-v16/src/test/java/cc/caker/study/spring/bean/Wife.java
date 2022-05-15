package cc.caker.study.spring.bean;

import lombok.Data;

/**
 * Wife
 *
 * @author cakeralter
 * @date 2022/5/15
 * @since 1.0
 */
@Data
public class Wife {

    private Husband husband;
    private IMother mother;

    public String queryHusband() {
        return "Wife.husband、Mother.callMother：" + mother.callMother();
    }
}
