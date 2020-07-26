package top.caker.demos.serialize.bean;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @author cakeralter
 * @date 2020/5/8
 */
public class Teacher implements Serializable {

    private static final long serialVersionUID = -5437761327680741061L;
    private static int autoIncrement = 1;
    private final int id;
    private String no;
    private String name;
    private String sex;
    private Integer age;
    private List<Student> students;

    {
        this.id = autoIncrement++;
        this.students = new LinkedList<>();
    }

    public Teacher(String no, String name, String sex, Integer age) {
        this.no = no;
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Teacher addStudent(Student student) {
        this.students.add(student);
        return this;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", no=" + no +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", students=" + students +
                '}';
    }
}
