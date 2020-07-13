[TOC]

### 1. char、byte、short两两运算结果（包括自己与自己）为int

> char、byte、short -> int -> long -> float -> double

```java
byte a = 12;
byte b = 14;
byte c = b + c; // 编译出错 int --/-> byte
```

### 2. 整型默认为int，浮点型默认为double

### 3. 原码、反码、补码（计算机存储一律使用补码）

- 正数三码合一

```java
12 => 0000 1100 => 0000 1100 => 0000 1100
```

- 负数反码为原码取反，补码为反码加一(符号位不变)

```java
-12 => 1000 1100 => 1111 0011 => 1111 0100
```

### 4. 进制转换技巧

1. 10进制↔2进制↔8进制

```java
12 = 0b00_001_100 = 01_4
```

2. 10进制↔2进制↔16进制

```java
12 = 0b0000_1100 = 0x0_C
```

### 5. ++ -- += -=等赋值运算符不会改变变量本身数据类型

```java
byte a = 12;
a = a + 12; // 编译出错 int --/-> byte
a+=12; // success
a = a + 1;; // 编译出错 int --/-> byte
a++; // success
```

### 6. 异或运算交换两个数值类型变量

> a ^ b ^ b = a ^ (b ^ b) = a

```java
int a = 12;
int b = 17;

a = a ^ b;
b = a ^ b;
a = a ^ b;
```

### 7. 一定范围内，x << n运算等于 x * 2^n^，x >> n运算等于x / 2^n^

```java
2 * 8 = 2 << 3 = 8 << 1
8 / 2 = 8 >> 1
```

### 8. 位运算进制转换

```java
// 0b0010_0101(37) 转为16进制
int bin = 0b0011_1101;
int suf = bin & ((2 << 3) - 1); // 取bin的后四位0101 即 bin & 15
int pre = bin >>> 4; // bin无符号右移4位 将前四位数移至尾部
pre = pre & ((2 << 3) - 1);

String prefix = pre > 9 ? (char) ('A' + (pre - 10)) + "" : pre + "";
String suffix = suf > 9 ? (char) ('A' + (suf - 10)) + "" : suf + "";

System.out.println("0x" + prefix + suffix); // 0x3D
```



