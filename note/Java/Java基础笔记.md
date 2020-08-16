[TOC]

## Java基础笔记

### 1. `char`、`byte`、`short`两两运算结果（包括自己与自己）为`int`

> char、byte、short -> int -> long -> float -> double

```java
byte a = 12;
byte b = 14;
byte c = b + c; // 编译出错 int --/-> byte
```

### 2. 整型默认为`int`，浮点型默认为`double`

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

### 6. 巧妙位运算

> [优秀程序员不得不知道的20个位运算技巧](https://www.cnblogs.com/qiaogaojian/p/5873105.html)

- 异或运算交换两个数值类型变量

> a ^ b ^ b = a ^ (b ^ b) = a

```java
int a = 12;
int b = 17;

a = a ^ b;
b = a ^ b;
a = a ^ b;
```

- 位运算取最大(小)值

```java
// 取最小值
b & ((b - a) >> 31) | a & (~(b - a) >> 31)
// 取最大值
a & ((a - b) >> 31) | b & (~(a - b) >> 31)
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

### 9. `switch`表达式只能使用`char`、`byte`、`short`、`int`、枚举、`String`等几种类型

### 10. 引用类型变量要么为`null`，要么为地址值

### 11. 成员变量和局部变量的区别

1. 成员变量和局部变量声明位置不同
2. 成员变量有默认值，可以不赋初始化值；局部变量必须给定初始值
3. 成员变量可以用权限修饰符控制访问；局部变量不可以
4. 成员变量和局部变量在内存中的位置不同

### 12. 重载(overload)的一些总结：两同一不同

- 同一个类中
- 方法名相同
- 参数列表不同：参数个数不同或者参数类型不同

### 13. Java的值传递机制

- 基本类型传递参数值
- 引用类型传递引用的地址值

### 14 `this`构造器只能在构造器首行调用，即只能调用一次

### 15. 子类继承父类所有的属性和方法。`private` 等修饰的属性或方法也会被继承，但访问受限

### 16. 方法重写（Override）的细节

1. 重写方法和被重写方法的方法名、参数列表相同
2. 重写方法的访问权限不能小于被重写方法的访问权限
3. 从返回值类型来看：
- 被重写方法返回值类型为void或基本类型，则重写方法返回值类型必须为void或基本类型
- 被重写方法返回值类型为引用类型，则重写方法返回值类型必须为相同类型或其子类型
4. 重写方法抛出异常类型必须和被重写方法一致或为其子类型异常

### 17. 对象的多态性

1. 多态可以理解为一个事物的多种形态
2. 何为多态性：父类的引用指向子类的对象
3. 多态的使用（虚拟方法调用）：编译看左边，运行看右边
4. 多态的前提：
- 继承关系
- 方法重写
5. 对象的多态性只适用于方法，不适用于属性
6. 多态是运行时行为

### 18. 对象加载过程代码块及构造函数的执行顺序

父类静态代码块 -> 子类静态代码块 -> 父类代码块 -> 父类构造函数 -> 子类代码块 -> 子类构造函数

### 19. 类属性可赋值顺序

默认初始化 -> 显式赋值 / 代码块赋值 -> 构造器赋值 -> 方法赋值

> 显式赋值和代码块执行看具体代码顺序

### 20. 在局部类的方法中如果要调用其声明域内的变量，则要求改变量必须用 `final` 修饰

因为外部类和局部内部类生命周期不一致，外部类传递的实际上是该变量的一个副本，故而要求其必须用 `final` 修饰

### 21. `throw` 和 `throws` 

- `throw` 表示抛出一个异常对象，属于生成异常对象的过程。声明在方法体中。
- `throws` 属于异常处理的一种方式，声明在方法声明处。

### 22. 一个Java程序启动时至少有3个线程：`main` 方法线程、`gc` 线程以及异常处理线程

### 23. Java线程状态转换图

![Java线程状态转换图](https://gitee.com/cakeralter/images/raw/master/20200727210059.png)

### 24. `Thread` 使用几点总结：

1. 不能直接调用 `run()` 来启动线程

> 调用 `Thread` 的 `start()` 方法会启动 `Thread` 线程执行 `run()`，直接调用 `run()` 则只是在当前线程执行

2. 不能多次调用线程对象的 `start()` 方法，会报 `IllegalThreadStateException` 异常

### 25. 线程常用方法：

- `Thread` 类中的方法

1. **start()** ：启动 `Thread` 线程；通知JVM调用线程的 `run()`
2. **run()** ：定义当前线程具体的执行逻辑
3. **currentThread()** ：获取当前线程对象
4. **setName(String name)** ：设置线程名
5. **getName()** ：获取线程名
6. **sleep(long millis)** ：使调用线程阻塞指定毫秒时间
7. **join()** ：在线程A中调用线程B的 `join()` ，则线程A进入阻塞状态直到线程B执行完毕，之后退出阻塞重新等待CPU调度
8. **yield()** ：使调用线程释放CPU执行权，CPU重新进行调度
9. **setPriority(int newPriority)** ：设置线程优先级
10. **getPriority()** ：获取线程优先级
11. **isAlive()** ：测试线程是否存活
12. **interrupt()** ：中断线程
13. **interrupted()** ：测试线程是否已中断
14. **setDaemon(boolean on)** ：是否将线程设置为守护线程
15. **isDaemon()** ：测试线程是否为守护线程
16.  **setDefaultUncaughtExceptionHandler(UncaughtExceptionHandler eh)** ：设置默认异常处理器
17.  **setUncaughtExceptionHandler(UncaughtExceptionHandler eh)** ：设置调用线程异常处理器

- `Object` 类中的方法

1. **wait() **：使调用线程进入等待状态
2. **notify()** ：通知一个等待状态的线程继续执行
3. **notifyAll()** ：通知所有等待线程继续执行

### 26. `wait()`、 `notify()` 和 `notifyAll()`

1. `wait()`、 `notify()`、`notifyAll()` 这三个方法必须在同步代码块或同步方法中调用

   ```java
   // 同步方法
   public void synchronized method() {
       // wait() / notify() / notifyAll()
   }
   // 同步代码块
   synchronized(this) {
       // wait() / notify() / notifyAll()
   }
   ```

2. `wait()`、 `notify()`、`notifyAll()` 必须由同步监视器对象调用，否则会抛异常

### 27. `sleep()` 和 `wait()`

- 相同：`sleep()` 和 `wait()` 都会使调用线程进入阻塞状态
- 不同：
  1. `sleep()` 是在 `Thread` 类中定义，`wait()` 是在 `Object` 类中定义
  2. `sleep()` 可以在任何地方调用，`wait()` 只能在同步方法或同步代码块中调用
  3. 线程调用 `wait()` 后会释放自己当前持有的锁对象，而调用 `sleep()` 则不会

### 28. `synchronized` 和 `Lock`

### 29. 创建线程的几种方式

1. 继承 `Thread` 类重写 `run()` 方法
2. 实现 `Runnable` 接口实现 `run()` 方法
3. 实现 `Callable` 接口实现 `call()` 方法
4. 使用线程池：`ExecutorService` 接口和 `ThreadPoolExecutor` 类

### 30. `String` 类总结

1. `String` 类被 `final` 修饰，说明其不可被继承
2. `String` 类实现了 `Serializable` 接口，说明其可以序列化
3. `String` 类实现了 `Comparable` 接口，说明其可以比较大小
4. `String` 类使用 `char` 数组存储字符，并且数组被 `final` 修饰
5. `String` 类中的字符序列是不可变的
6. 通过字面量声明一个字符串时，该字符串会存储在字符串常量池中（常量池已经存在直接返回引用，常量池不存在则先在常量池创建字符串后返回引用）
7. 通过构造器声明一个字符串时，会在堆中创建一个对象后返回对象的引用（该对象中存储的是常量池中字符串的引用值）

### 31. `String` 、`StringBuffer` 及 `StringBuilder`

- **String** ：不可变的字符序列，底层是 `char` 数组
- **StringBuffer** ：可变的字符序列，底层是 `char` 数组；线程安全，效率低
- **StringBuilder** ：可变的字符序列，底层是 `char` 数组；线程不安全，效率高

### 32. 数组的缺点总结

1. 数组初始化后，长度不可修改
2. 数组没有提供友好的API，对其进行操作（如删除、插入、获取有效值等操作）比较麻烦，效率不高
3. 数组对于一些特殊需求不能实现（如无序、不可重复）

### 33. `Collection` 总结

> 有序、无序指的是数据在内存中是否连续存储

- `List` ：有序、可重复

  - `ArrayList`

    1. 底层采用 `Object[]` 实现

       ```java
       transient Object[] elementData;
       ```

    2. 线程不安全，查找效率高

    3. 在jdk7中默认构造器会创建一个容量为10的空数组；而jdk8则创建了一个空数组，在第一次调用 `add` 方法时才会对数组进行扩容

       ```java
       // jdk7
       public ArrayList() {
           this(10);
       }
       
       public ArrayList(int initialCapacity) {
           super();
           if (initialCapacity < 0)
               throw new IllegalArgumentException("Illegal Capacity: "+
                                                  initialCapacity);
           this.elementData = new Object[initialCapacity];
       }
       
       // jdk8
       public ArrayList() {
           this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
       }
       ```

    4. 扩容操作：

       ```java
       private void grow(int minCapacity) {
           // overflow-conscious code
           int oldCapacity = elementData.length;
           // 1.5倍
           int newCapacity = oldCapacity + (oldCapacity >> 1);
           if (newCapacity - minCapacity < 0)
               newCapacity = minCapacity;
           if (newCapacity - MAX_ARRAY_SIZE > 0)
               newCapacity = hugeCapacity(minCapacity);
           // minCapacity is usually close to size, so this is a win:
           elementData = Arrays.copyOf(elementData, newCapacity);
       }
       ```

  - `LinkedList`

    1. 底层采用双向链表实现

       ```java
       transient Node<E> first;
       transient Node<E> last;
       
       private static class Node<E> {
           E item;
           Node<E> next;
           Node<E> prev;
       
           Node(Node<E> prev, E element, Node<E> next) {
               this.item = element;
               this.next = next;
               this.prev = prev;
           }
       }
       ```

    2. 在随机插入、删除操作频繁时效率高于 `ArrayList` 

  - `Vector`

    1. 底层采用 `Object[]` 实现

       ```java
       protected Object[] elementData;
       ```

    2. 线程安全，效率较低

    3. 扩容操作：

       ```java
       private void grow(int minCapacity) {
           // overflow-conscious code
           int oldCapacity = elementData.length;
           // 2倍
           int newCapacity = oldCapacity + ((capacityIncrement > 0) ?
           	capacityIncrement : oldCapacity);
           if (newCapacity - minCapacity < 0)
           	newCapacity = minCapacity;
           if (newCapacity - MAX_ARRAY_SIZE > 0)
           	newCapacity = hugeCapacity(minCapacity);
           elementData = Arrays.copyOf(elementData, newCapacity);
       }
       ```

- `Set`：无序、不可重复

  - `HashSet`

    1. 底层采用 `HashMap` 实现

        ```java
        private transient HashMap<E,Object> map;
        ```
    
    2. 判断元素是否重复需要调用 `hashCode()` 、`equals()` 方法

  - `LinkedHashSet`

    1. 底层采用 `HashMap` 实现
    2. 继承自 `HashSet`

        ```java
        public class LinkedHashSet<E>
            extends HashSet<E>
            implements Set<E>, Cloneable, java.io.Serializable
        ```

    3. 判断元素是否重复需要调用 `hashCode()` 、`equals()` 方法
    4. 底层维护了一个双向链表，使得遍历时可以保持插入顺序
    5. 对于频繁的遍历操作，`LinkedHashSet` 效率高于 `HashSet`

  - `TreeSet`

    1. 底层类似 `TreeMap`
    2. 只能放入相同类型的元素
    3. 判断元素是否重复需要调用 `compare()` 方法

- `Map`

  - `HashMap`

    1. 特点：线程不安全，效率高；键和值均允许为 `null` 

    2. 源码分析

       ``````java
       /* 类成员 */
       // 核心数组
       transient Node<K,V>[] table;
       // entrySet缓存
       transient Set<Map.Entry<K,V>> entrySet;
       // 数组节点数量 - 即Map中数据个数
       transient int size;
       // 
       transient int modCount;
       // 扩容阈值
       int threshold;
       // 负载因子
       final float loadFactor;
       
       /* 常量 */
       // 默认初始化容量16
       static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; 
       // 默认负载因子
       static final float DEFAULT_LOAD_FACTOR = 0.75f;
       // 链表转红黑树阈值
       static final int TREEIFY_THRESHOLD = 8; 
       // 红黑树转链表阈值
       static final int UNTREEIFY_THRESHOLD = 6; 
       // 转红黑树数组最小容量
       static final int MIN_TREEIFY_CAPACITY = 64; 
       
       /* 主要内部类 */
       // 链表节点
       static class Node<K,V> implements Map.Entry<K,V> {
           final int hash;
           final K key;
           V value;
           Node<K,V> next;
       
           Node(int hash, K key, V value, Node<K,V> next) {
               this.hash = hash;
               this.key = key;
               this.value = value;
               this.next = next;
           }
       }
       
       // 红黑树节点
       static final class TreeNode<K,V> extends LinkedHashMap.Entry<K,V> {
           TreeNode<K,V> parent;  // red-black tree links
           TreeNode<K,V> left;
           TreeNode<K,V> right;
           TreeNode<K,V> prev;    // needed to unlink next upon deletion
           boolean red;
           
           TreeNode(int hash, K key, V val, Node<K,V> next) {
               super(hash, key, val, next);
           }
       }
       
       /* 重要方法 */
       
       ``````

    3. 

  - `LinkedHashMap`
    1. 底层在 `HashMap` 基础上维护了一个双向链表，使得可以在遍历时保持插入顺序
    2. 对于频繁的遍历操作，`LinkedHashMap` 效率高于 `HashMap`
    
  - `TreeMap`
    1. 底层使用红黑树实现
    2. 插入时按照key进行排序
    
  - `Hashtable`
    1. 线程不安全，效率低
    2. 键值不允许为 `null`
    
  - `Properties` ：常用来处理配置信息，键值均为 `String` 类型
