package top.caker.clazz;

import cn.hutool.core.util.ArrayUtil;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author cakeralter
 * @date 2020/8/18
 */
public class ClassTest {

    /**
     * 获取Class实例的几种方式
     */
    @Test
    public void testGet() throws ClassNotFoundException {
        // 1 通过类型
        Class<Student> aClass1 = Student.class;
        System.out.println(aClass1);

        // 2 通过实例
        Student student = new Student();
        Class<? extends Student> aClass2 = student.getClass();
        System.out.println(aClass2);

        // 3 通过全类名[常用]
        Class<?> aClass3 = Class.forName("top.caker.clazz.Student");
        System.out.println(aClass3);

        // 4 通过类加载器
        Class<?> aClass4 = this.getClass().getClassLoader().loadClass("top.caker.clazz.Student");
        System.out.println(aClass4);

        System.out.println(aClass1 == aClass2);
        System.out.println(aClass2 == aClass3);
        System.out.println(aClass3 == aClass4);
    }

    /**
     * 反射获取类信息
     */
    @Test
    public void testGetInfo() throws ClassNotFoundException {
        Class<?> aClass = Class.forName("top.caker.clazz.Student");
        // 获取全类名
        System.out.println("name = " + aClass.getName());
        // 获取类名
        System.out.println("simpleName = " + aClass.getSimpleName());

        // 获取所有public属性[包括父类的public属性]
        System.out.print("fields = ");
        Arrays.asList(aClass.getFields()).forEach(x -> System.out.print(x.getName() + "\t"));
        System.out.println();

        // 获取所有本类的属性[包括static属性]
        System.out.print("declaredFields = ");
        Arrays.asList(aClass.getDeclaredFields()).forEach(x -> System.out.print(x.getName() + "\t"));
        System.out.println();

        // 获取所有public方法信息[包括父类的public方法]
        System.out.println("methods: ");
        Arrays.asList(aClass.getMethods()).forEach(x -> {
            StringBuilder builder = new StringBuilder(String.format("%s %s %s(",
                    Modifier.toString(x.getModifiers()), x.getReturnType().getSimpleName(), x.getName()));
            String collect = Arrays.stream(x.getParameters())
                    .map(p -> p.getType().getSimpleName() + " " + p.getName())
                    .collect(Collectors.joining(", "));
            builder.append(collect)
                    .append(") ");
            Class<?>[] types = x.getExceptionTypes();
            if (ArrayUtil.isNotEmpty(types)) {
                String join = Arrays.stream(types).map(Class::getSimpleName).collect(Collectors.joining(", "));
                builder.append("throws ")
                        .append(join);
            }
            System.out.println(builder.toString());
        });
        System.out.println();

        // 获取所有本类的方法信息
        System.out.println("declaredMethods: ");
        Arrays.asList(aClass.getDeclaredMethods()).forEach(x -> {
            StringBuilder builder = new StringBuilder(String.format("%s %s %s(",
                    Modifier.toString(x.getModifiers()), x.getReturnType().getSimpleName(), x.getName()));
            String collect = Arrays.stream(x.getParameters())
                    .map(p -> p.getType().getSimpleName() + " " + p.getName())
                    .collect(Collectors.joining(", "));
            builder.append(collect)
                    .append(") ");
            Class<?>[] types = x.getExceptionTypes();
            if (ArrayUtil.isNotEmpty(types)) {
                String join = Arrays.stream(types).map(Class::getSimpleName).collect(Collectors.joining(", "));
                builder.append("throws ")
                        .append(join);
            }
            System.out.println(builder.toString());
        });
        System.out.println();

        // 获取注解信息[必须是RUNTIME]
        System.out.print("declaredAnnotations = ");
        Arrays.asList(aClass.getDeclaredAnnotations()).forEach(x -> System.out.print(x.annotationType().getSimpleName() + "\t"));
        System.out.println();
    }

    /**
     * 反射操作对象
     */
    @Test
    public void testNew() throws Exception {
        Class<?> aClass = Class.forName("top.caker.clazz.Student");
        // 调用无参构造器
        Student instance = (Student) aClass.newInstance();
        // 反射set成员变量
        Field f1 = aClass.getDeclaredField("name");
        f1.setAccessible(true);
        Field f2 = aClass.getDeclaredField("school");
        f2.setAccessible(true);
        Field f3 = aClass.getDeclaredField("teacher");
        f3.setAccessible(true);
        Field f4 = aClass.getDeclaredField("wife");
        f4.setAccessible(true);
        f1.set(instance, "wangmin");
        f2.set(instance, "天津师范大学");
        f3.set(instance, "潘桂生");
        f4.set(instance, "wo");
        System.out.println(instance);

        // 反射可以修改final属性
        Field f5 = aClass.getDeclaredField("sid");
        f5.setAccessible(true);
        f5.set(instance, 5678);
        System.out.println(instance);

        // 反射可以修改static final属性
        Method getUuid = aClass.getDeclaredMethod("getUuid");
        System.out.println(getUuid.invoke(null));
        Field f6 = aClass.getDeclaredField("uuid");
        f6.setAccessible(true);
        Field modifiers = Field.class.getDeclaredField("modifiers");
        modifiers.setAccessible(true);
        modifiers.set(f6, f6.getModifiers() & ~Modifier.FINAL);
        System.out.println(Modifier.toString(f6.getModifiers()));
        f6.set(null, 8127384);
        System.out.println(getUuid.invoke(null));

        // 调用实例方法
        Method marry = aClass.getDeclaredMethod("marry", String.class);
        System.out.println(marry.invoke(instance, "w"));

        // 调用静态方法
        Method happy = aClass.getDeclaredMethod("happy");
        System.out.println(happy.invoke(null));
    }
}
