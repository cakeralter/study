[TOC]

### 1. 题干如下：

```java
public static void main(String[] args) {
    int a = 10;
    int b = 20;

    method(a, b); // 编写method方法，要求在method调用后仅输入"a=100,b=200"

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
    System.out.println(arr1); // [I@39a054a5

    char arr2[] = new char[10];
    System.out.println(arr2); // agsg
}
```
