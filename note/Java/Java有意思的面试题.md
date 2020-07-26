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

