[TOC]

### 1. 题干如下：

```java
public static void main(String[] args) {
    int a = 10;
    int b = 20;

    // 编写method方法，要求在method调用后仅输入"a=100,b=200"
    method(a, b); 

    System.out.println("a=" + a + ",b=" + b);
}
```

- 解法一：

```java
public static void method(int a, int b) {
    System.out.println("a=" + 100 + ",b=" + 200);
    System.exit(-1);
}
```

- 解法二：

```java
public static void method(int a, int b) {
    PrintStream printStream = new PrintStream(System.out) {
        @Override
        public void println(String x) {
            if ("a=10,b=20".equals(x)) {
                x = "a=100,b=200";
            }
            super.println(x);
        }
    };
    System.setOut(printStream);
}
```

### 2. 以下代码输出为：

```java
public static void main(String[] args) {
    int arr1[] = new int[10];
    // [I@39a054a5
    System.out.println(arr1); 

    char arr2[] = new char[10];
    // agsg
    System.out.println(arr2); 
}
```

### 3. 多态题，以下代码输出为：

```java
public static void main(String[] args) {
    Sub sub = new Sub();
    // 20
    System.out.println(sub.count);
    // 20
    sub.print();

    Base base = sub;
    // true
    System.out.println(base == sub);
    // 10
    System.out.println(base.count);
    // 20
    base.print();
}


class Base {
    int count = 10;

    public void print() {
        System.out.println(this.count);
    }
}

class Sub extends Base {
    int count = 20;

    @Override
    public void print() {
        System.out.println(this.count);
    }
}
```

### 4. 多态题，以下代码输出为：

```java
public static void main(String[] args) {
    Base base = new Sub();
    // 可变参数和数组构成重写 --- Sub.print(int a, int[] arr)
    base.print(1, 2, 3);

    Sub sub = (Sub) base;
    // 重载确定参数优先 --- Sub.print(int a, int b, int c)
    sub.print(1, 2, 3);
}

class Base {

    public void print(int a, int... arr) {
        System.out.println("Base.print(int a, int... arr)");
    }
}

class Sub extends Base {

    public void print(int a, int[] arr) {
        System.out.println("Sub.print(int a, int[] arr)");
    }

    public void print(int a, int b, int c) {
        System.out.println("Sub.print(int a, int b, int c)");
    }
}
```

### 5. 包装类，以下代码输出为：

```java
public static void main(String[] args) {
    Object o1 = true ? new Integer(1) : new Double(2.0);
    // 三元运算符两端类型一致，所以会进行自动类型提升 --- 1.0
    System.out.println(o1);

    Object o2 = null;
    if(true) {
    	o2 = new Integer(1);
    }else {
    	o2 = new Double(2.0);
    }
    // 1
    System.out.println(o2);
}
```

### 6. 接口和抽象类，代码如下：

接口和抽象类属性重名，调用时必须明确引用位置

```java
interface BaseInterface {

    int x = 1;
    int y = 2;
}

abstract class BaseClass {

    int x = 11;
    int z = 33;
}

class Sub extends BaseClass implements BaseInterface {

    public void print() {
        // 报错 对 'x' 的引用不明确，'BaseClass.x' 和 'BaseInterface.x' 均匹配
//        System.out.println(x);
        // BaseInterface.x
        System.out.println(BaseInterface.x);
        // BaseClass.x
        System.out.println(super.x);
        System.out.println(y);
        System.out.println(z);
    }
}
```

### 7. 单目运算符，如下：

```java
public static void main(String[] args) {
    int i = -5;
    // error - 单目运算符不能用于字面量，i++执行后 i = ++(-4)错误
//        i = ++(i++);
    System.out.println(i);
}
```

### 8. 如何解决线程安全问题？

### 9. 如何避免死锁？

### 10. `String a = new String("abnc");` 总共创建了几个对象？

两个。一个是字符串常量池的 `abnc` ，一个是堆空间中的对象。

### 11. 字符串，代码如下：

> 1. 常量与常量拼接的结果在常量池中，且常量池不会存储多个相同的常量；
> 2. 变量参与的拼接在堆中进行；
> 3. intern()方法返回常量池中的地址。

```java
public static void main(String[] args) {

   	String s1 = "Java";
    String s2 = "Script";
    String s3 = "JavaScript";
    String s4 = "Java" + "Script";
    String s5 = s1 + "Script";
    String s6 = "Java" + s2;
    String s7 = s1 + s2;
    String s8 = (s1 + s2).intern();
    final String s9 = "Java";
    String s10 = s9 + s2;
    String s11 = s9 + "Script";

    /*
	 * 1. 常量与常量拼接的结果在常量池中，且常量池不会存储多个相同的常量；
	 * 2. 变量参与的拼接在堆中进行；
	 * 3. intern()方法返回常量池中的地址。
     * */
    // true
    System.out.println(s3 == s4);
    // false
    System.out.println(s3 == s5);
    // false
    System.out.println(s3 == s6);
    // false
    System.out.println(s3 == s7);
    // true
    System.out.println(s3 == s8);
    // false
    System.out.println(s3 == s10);
    // true --- s9是常量
    System.out.println(s3 == s11);
}
```

### 12. 值传递与字符串，以下代码输出：

```java
public static void main(String[] args) {
    Test t = new Test();
    t.change(t.a, t.b);

    // wangmin
    System.out.println(t.a);
    // chengyuying
    System.out.println(t.b);
}

static class Test {

	String a = "wangmin";
	char[] b = {'a', 'h', 'e', 'n', 'g', 'y', 'u', 'y', 'i', 'n', 'g'};

   	void change(String str, char[] ch) {
        str = "luqian";
        ch[0] = 'c';
    }
}
```