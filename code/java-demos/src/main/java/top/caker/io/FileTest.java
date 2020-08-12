package top.caker.io;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * 分隔符最终都会转成系统相关的
 *
 * @author cakeralter
 * @date 2020/8/11
 */
public class FileTest {

    File file1 = new File("student.txt");
    File file2 = new File("D:\\test\\student.txt");
    File file3 = new File("./student.txt");
    File file4 = new File("././student.txt");
    File file5 = new File("/student.txt");
    File file6 = new File("\\student.txt");
    File file7 = new File("/./student.txt");
    File file8 = new File(".\\student.txt");

    /**
     * getPath - 返回文件构造器中的抽象路径
     */
    @Test
    public void testGetPath() {
        // student.txt
        System.out.println("file1 path = " + file1.getPath());
        // D:\test\student.txt
        System.out.println("file2 path = " + file2.getPath());
        // .\student.txt
        System.out.println("file3 path = " + file3.getPath());
        // .\.\student.txt
        System.out.println("file4 path = " + file4.getPath());
        // \student.txt
        System.out.println("file5 path = " + file5.getPath());
        // \student.txt
        System.out.println("file6 path = " + file6.getPath());
        // \.\student.txt
        System.out.println("file7 path = " + file7.getPath());
        // .\student.txt
        System.out.println("file8 path = " + file8.getPath());
    }

    /**
     * getAbsolutePath - 返回绝对路径
     * 相对路径是编译后的路径
     * 会解析绝对路径符 `/` `\\`，不会解析 `./` `.\\`
     */
    @Test
    public void testGetAbsolutePath() {
        // D:\workspace\mine\study\code\java-demo\student.txt
        System.out.println("file1 absolutePath = " + file1.getAbsolutePath());
        // D:\test\student.txt
        System.out.println("file2 absolutePath = " + file2.getAbsolutePath());
        // D:\workspace\mine\study\code\java-demo\.\student.txt
        System.out.println("file3 absolutePath = " + file3.getAbsolutePath());
        // D:\workspace\mine\study\code\java-demo\.\.\student.txt
        System.out.println("file4 absolutePath = " + file4.getAbsolutePath());
        // D:\student.txt
        System.out.println("file5 absolutePath = " + file5.getAbsolutePath());
        // D:\student.txt
        System.out.println("file6 absolutePath = " + file6.getAbsolutePath());
        // D:\.\student.txt
        System.out.println("file7 absolutePath = " + file7.getAbsolutePath());
        // D:\workspace\mine\study\code\java-demo\.\student.txt
        System.out.println("file8 absolutePath = " + file8.getAbsolutePath());
    }

    /**
     * getAbsolutePath - 返回绝对路径
     * 相对路径是编译后的路径
     * 会解析路径符，`././` = `./`
     *
     * @throws IOException
     */
    @Test
    public void testGetCanonicalPath() throws IOException {
        // D:\workspace\mine\study\code\java-demo\student.txt
        System.out.println("file1 canonicalPath = " + file1.getCanonicalPath());
        // D:\test\student.txt
        System.out.println("file2 canonicalPath = " + file2.getCanonicalPath());
        // D:\workspace\mine\study\code\java-demo\student.txt
        System.out.println("file3 canonicalPath = " + file3.getCanonicalPath());
        // D:\workspace\mine\study\code\java-demo\student.txt
        System.out.println("file4 canonicalPath = " + file4.getCanonicalPath());
        // D:\workspace\mine\study\code\student.txt
        System.out.println("file4 canonicalPath = " + new File("../student.txt").getCanonicalPath());
        // D:\student.txt
        System.out.println("file5 canonicalPath = " + file5.getCanonicalPath());
        // D:\student.txt
        System.out.println("file6 canonicalPath = " + file6.getCanonicalPath());
        // D:\student.txt
        System.out.println("file7 canonicalPath = " + file7.getCanonicalPath());
        // D:\workspace\mine\study\code\java-demo\student.txt
        System.out.println("file8 absolutePath = " + file8.getCanonicalPath());
    }
}
