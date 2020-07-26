package top.caker.demos.serialize;

import org.junit.Before;
import org.junit.Test;
import top.caker.demos.serialize.bean.Student;
import top.caker.demos.serialize.bean.Teacher;

import java.io.*;

/**
 * @author cakeralter
 * @date 2020/5/8
 */
public class ObjectSerializeTest {

    private Teacher teacher;

    @Before
    public void createData() {
        teacher = new Teacher("0001", "汪敏", "女", 26);
        teacher.addStudent(new Student("1000000", "卢倩", "女", 23))
                .addStudent(new Student("1000001", "李强", "男", 24))
                .addStudent(new Student("1000002", "王义刚", "男", 23))
                .addStudent(new Student("1000003", "文品", "男", 25))
                .addStudent(new Student("1000004", "徐荣", "女", 23));
    }

    @Test
    public void testSerialize() throws IOException {
        System.out.println(teacher);
        File file = new File("D:\\teacher.txt");
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(teacher);
        }
    }

    @Test
    public void testDeserialize() throws IOException, ClassNotFoundException {
        File file = new File("D:\\teacher.txt");
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            Object o = ois.readObject();
            System.out.println(o);
            System.out.println(o instanceof Teacher);
        }
    }
}
