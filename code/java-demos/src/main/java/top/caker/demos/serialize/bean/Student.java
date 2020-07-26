package top.caker.demos.serialize.bean;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * @author cakeralter
 * @date 2020/5/8
 */
public class Student implements Externalizable {

    private static final long serialVersionUID = 8606438229106119575L;
    private static int autoIncrement = 1;
    private final int id;
    private String no;
    private String name;
    private String sex;
    private Integer age;

    {
        this.id = autoIncrement++;
    }

    public Student() {
    }

    public Student(String no, String name, String sex, Integer age) {
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

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", no=" + no +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(no);
        out.writeUTF(name);
        out.writeInt(age);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.no = in.readUTF();
        this.name = in.readUTF();
        this.age = in.readInt();
    }
}
