package top.caker.clazz;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

/**
 * @author cakeralter
 * @date 2020/8/18
 */
@ToString
@Setter
@Getter
@NoArgsConstructor
@Init
public class Student extends Person<String> {

    private static final long serialVersionUID = -3211645608516098424L;
    private String name;
    private String school;
    public String teacher;
    private String wife;
    private final Integer sid = 1234;
    private static final Integer uuid = 76888;

    public Student(String school, String teacher) {
        this.school = school;
        this.teacher = teacher;
    }

    public String marry(String girl) throws RuntimeException {
        return name + "和" + girl + "结婚了!";
    }

    public boolean isTrue(String name) {
        return Objects.equals(this.name, name);
    }

    public static String happy() throws RuntimeException {
        return "happy";
    }

    public static Integer getUuid() {
        return uuid;
    }
}
