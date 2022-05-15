package cc.caker.study.spring.bean;

import lombok.Data;

import java.time.LocalDate;

/**
 * Husband
 *
 * @author cakeralter
 * @date 2022/5/15
 * @since 1.0
 */
@Data
public class Husband {

    private String wifeName;
    private LocalDate marriageDate;
}
