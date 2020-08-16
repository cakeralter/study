[TOC]

## Java集合笔记

### 1. 数组的缺点总结

1. 数组初始化后，长度不可修改
2. 数组没有提供友好的API，对其进行操作（如删除、插入、获取有效值等操作）比较麻烦，效率不高
3. 数组对于一些特殊需求不能实现（如无序、不可重复）

### 2. `List` ：有序、可重复

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

### 3. `Set` ：无序、不可重复

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

### 4. `Map` 

- `HashMap`

1. 特点：线程不安全，效率高；键和值均允许为 `null` 

2. 源码分析

```java
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
	
```

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