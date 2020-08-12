package top.caker.annotation;

import org.junit.Test;

import java.lang.annotation.Annotation;

/**
 * 注解测试
 *
 * @author cakeralter
 * @date 2020/8/2
 */
public class AnnotationTest {

    @Test
    public void testAnnotation() {
        Class<Student> aClass = Student.class;
        Annotation[] annotations = aClass.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
    }
}

/*
jdk1.8 之前写法
@Inits({
        @Init(name = "wangmin", age = 24, grade = "大四"),
        @Init(name = "luqian", age = 24, grade = "大四")
})*/
@Init(name = "wangmin", age = 24, grade = "大四")
@Init(name = "luqian", age = 24, grade = "大四")
class Person {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class Student extends Person {

    private String grade;

    public Student(String name, int age, String grade) {
        super(name, age);
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "grade='" + grade + '\'' +
                '}';
    }
}
