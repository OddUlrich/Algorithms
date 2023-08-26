# HashMap 类源码分析

HashMap 结合了 HashTable 和 Map 的特性，基于数组拓展通过 <K, V> 键值对的方式来存储，使用哈希对关键字进行索引，映射到目标值的地址。

从 JDK1.7 到 JDK1.8，主要作出了以下的优化：

* 内部实现结构从数组+链表改成了数组+链表红黑树。
    * 为了避免由于防止发生 hash 冲突而使得链表长度过长，这样可以将时间复杂度从 O(n) 降为 O(log n）。
* 链表插入方式从头插法改成了尾插法。
    * 在 Java 7 使用头插法在扩容时，会使链表发生反转，在多线程环境下会产生环。
* 扩容时，Java 7 需要对原数组中的元素进行重新 hash 来定位在新数组中的位置；Java 8 采用更加简单的逻辑判断，位置不变或者索引+旧容量大小。
    * Java 8 扩容是将数组大小扩大到原来的两倍，对应的模运算用的 n - 1 其实只是在原来的基础上高位多了 1，低位计算出来的结果还是一样的，因此就不需要重新 hash 定位了。
* 哈希函数内部使用扰动函数从扰动 4 次变成 1 次。



HashMap 的实现逻辑如下：

![HashMap](D:\3.Workplace\Algorithms\md笔记\Java\HashMap.JPG)



## 静态内部类 - Node



```java
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

    public final K getKey()        { return key; }
    public final V getValue()      { return value; }
    public final String toString() { return key + "=" + value; }

    public final int hashCode() {
        return Objects.hashCode(key) ^ Objects.hashCode(value);
    }

    public final V setValue(V newValue) {
        V oldValue = value;
        value = newValue;
        return oldValue;
    }

    public final boolean equals(Object o) {
        if (o == this)
            return true;
        if (o instanceof Map.Entry) {
            Map.Entry<?,?> e = (Map.Entry<?,?>)o;
            if (Objects.equals(key, e.getKey()) &&
                Objects.equals(value, e.getValue()))
                return true;
        }
        return false;
    }
}
```







## HashMap 初始化

HashMap 类预定义了以下几个默认值：

* DEFAULT_INITIAL_CAPACITY：默认大小为 16。
* MAXIMUM_CAPACITY：允许最大容量为 2 的 30 次方。
* DEFAULT_LOAD_FACTOR：默认装载因子为 0.75.

```java
public class HashMap<K,V> extends AbstractMap<K,V>
    implements Map<K,V>, Cloneable, Serializable {

    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
    static final int MAXIMUM_CAPACITY = 1 << 30;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    
    transient Node<K,V>[] table;
    transient Set<Map.Entry<K,V>> entrySet;
    transient int size;
    int threshold;
    final float loadFactor;
}
```



HashMap 类主要提供了三种构造函数，分别根据需要传入不同的入参。

* 不传入初始化大小：此时，负载因子使用默认值 0.75

```java
public HashMap() {
    this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
}
```

* 入参传入初始化大小（和负载因子）：初始化大小不能大于所能允许的最大容量（2 的 30 次方）；根据设定的初始化容量大小，调用 `tableSizeFor（）`方法，计算得到下次需要进行扩容时的阈值大小。`tableSizeFor（）`具体实现是先使 n = cap - 1，然后 n 不断右移一位后与自身进行或运算，最后再加 1。这样做的目的是，将最高位为 1 的后面全部变为 1，最后加 1 后就能得到一个 2 的整数次幂。

```java
public HashMap(int initialCapacity) {
    this(initialCapacity, DEFAULT_LOAD_FACTOR);
}    
public HashMap(int initialCapacity, float loadFactor) {
    if (initialCapacity < 0)
        throw new IllegalArgumentException("Illegal initial capacity: " +
                                           initialCapacity);
    if (initialCapacity > MAXIMUM_CAPACITY)
        initialCapacity = MAXIMUM_CAPACITY;
    if (loadFactor <= 0 || Float.isNaN(loadFactor))
        throw new IllegalArgumentException("Illegal load factor: " +
                                           loadFactor);
    this.loadFactor = loadFactor;
    this.threshold = tableSizeFor(initialCapacity);
}
static final int tableSizeFor(int cap) {
    int n = -1 >>> Integer.numberOfLeadingZeros(cap - 1);
    return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
}
```

* 传入一个 Map 类对象。这里采用默认的负载因子，基本过程就是确定新建的 HashMap 的容量后，将入参表内的键值对依次添加到新表中。

```java
public HashMap(Map<? extends K, ? extends V> m) {
    this.loadFactor = DEFAULT_LOAD_FACTOR;
    putMapEntries(m, false);
}
final void putMapEntries(Map<? extends K, ? extends V> m, boolean evict) {
    int s = m.size();
    if (s > 0) {
        if (table == null) { // pre-size
            float ft = ((float)s / loadFactor) + 1.0F;
            int t = ((ft < (float)MAXIMUM_CAPACITY) ?
                     (int)ft : MAXIMUM_CAPACITY);
            if (t > threshold)
                threshold = tableSizeFor(t);
        }
        else if (s > threshold)
            resize();
        for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
            K key = e.getKey();
            V value = e.getValue();
            putVal(hash(key), key, value, false, evict);
        }
    }
}
```



创建 HashMap 对象时一般不传值，则默认的初始容量大小为 16，负载因子为 0.75。如果传入初始大小为 k，那么 HashMap 的初始化大小为一个大于 k 的值，该值取 2 的 N 次方。例如，传入大小为 10，那么对应的初始化大小为 16 = 2^4。





## 插入新数据

在 JDK1.8 版本中，HashMap 内部通过使用**数组 + 链表红黑树**实现。两者的转换边界如下：

* 当链表长度大于 8 且数组大小大于或者等于 64 时，就将链表转化成红黑树对数据进行存储；
* 当红黑树结点个数小于 6 是，转化成链表表示。

```java
static final int TREEIFY_THRESHOLD = 8;
static final int UNTREEIFY_THRESHOLD = 6;
static final int MIN_TREEIFY_CAPACITY = 64;
```

为什么这里的阈值会选择 8 和 6 呢？

经过研究计算，在哈希函数设计合理的前提下，发生 hash 碰撞 8 次的几率为百万分之六，所以链表转红黑树的阈值为 8 已经足够了。而之所以红黑树转回链表的阈值为 6，是因为如果 hash 碰撞次数在 8 附近徘徊，就会一直发生链表和红黑树的互相转化，所以取较小一些的 6 能预防之中情况的发生。相关的说明在源码的注释中也能看到：

> \* usages with well-distributed user hashCodes, tree bins are rarely used.  Ideally, under random hashCodes, the frequency of  nodes in bins follows a Poisson distribution (http://en.wikipedia.org/wiki/Poisson_distribution) with a  parameter of about 0.5 on average for the default resizing threshold of 0.75, although with a large variance because of resizing granularity. Ignoring variance, the expected occurrences of list size k are (exp(-0.5) pow(0.5, k) / factorial(k)). The first values are: 
>
> * 0:    0.60653066 
> * 1:    0.30326533
> * 2:    0.07581633 
> * 3:    0.01263606 
> * 4:    0.00157952
> * 5:    0.00015795 
> * 6:    0.00001316
> * 7:    0.00000094
> * 8:    0.00000006 
>
> more: less than 1 in ten million



正因为中间存在数据结构的转换，所以对 HashMap 进行添加删除操作时，内部需要经过一系列的逻辑处理。数据插入的过程大致如下：

* 判断数组是否为空，若为空则进行初始化。
* （除留余数法）若不为空，计算 key 的 hash 值，通过 `（n-1） & hash`，即 `hash mod （n-1)` 计算应当村昂在数组中的哪个下标 index。
* 查看 table[index] 是否存在数据，若没有则创建一个新的 Node 节点存放在该位置。
* 若该位置存在数据，说明产生了 hash 冲突。进一步判断 key 是否相同，如果相同，则更新为新的 value。
* 若 key 不相同，则判断当前节点是不是树型节点，如果是，说明当前已经为红黑树了，则创建新的树型节点并插入到红黑树中。
* 如果不是树型节点，说明仍然为链表，则创建普通的 Node 加入到链表中。然后判断链表长度，看是否需要转化为红黑树。
* 插入完成后，判断当前节点数是否大于阈值，如果是，则扩容为原数组的两倍。





## HashMap 的哈希函数

看看源码的具体实现：

```java
static final int hash(Object key) {
    int h;
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
```

哈希函数设计为：先拿到 key 的 hashcode，是一个 32 位的 int 值，然后让其高 16 位和低 16 位进行异或操作。

这种设计叫做**扰动函数**。这样子设计主要考虑到两点：一是因为求 hash 是高频操作，使用位运算能使得算法尽可能高效；二是能够降低 hash 冲突。之所以不直接使用 key 的 hashcode 是因为 key.hashCode( ) 返回的 int 型散列值范围在 -2^31 到 2^31 - 1 之间，前后加起来大约有 40 亿个映射空间，所以只要哈希函数映射结果比较均匀松散，一般是很难会出现碰撞的。但是一个 40 亿长度的数组，内存时放不下的，使用除留余数法进行散列时，需要对数组长度进行取模运算，源码中的模运算就是把散列值与数组长度减 1 做一个与运算，位运算比取余运算更快。

```java
static int indexFor(int h, int length) { return h & (length - 1); }
```

这也就解释了为什么 HashMap 的数组长度要取 2 的整数幂，因为减 1 后正好相当于形成了一个“低位掩码”（全为 1），进行与运算后，结果就是截取了低位的值，从而实现模运算。

右移 16 位，正好是 32 位的一半，哈希吗自身的高位和低位做**异或**运算，就是为了混合原始哈希码的高位和低位，以此来加大低位的随机性，而混合后的低位掺杂了高位的部分特征，这样高位的信息也被变相保留了下来。

另外在 JDK1.7 中，扰动做了四次位移和四次异或，而 JDK1.8 为了效率，就改成了一次，也足够了。

```java
static int hash(int h) {
    h ^= (h >>> 20) ^ (h >>> 12);
    return h ^ (h >>> 7) ^ (h >>> 4);
}
```



## HashMap 不是线程安全的

在多线程环境下，JDK1.7 会产生死循环、数据丢失、数据覆盖的问题，JDK1.8 中会有数据覆盖的问题。以1.8为例，当 A 线程判断 index 位置为空后正好挂起，B 线程开始往 index 位置的写入节点数据，这时 A 线程恢复现场，执行赋值操作，就把 B 线程的数据给覆盖了；还有像在 ++size 这种地方也会造成多线程同时扩容等问题。

要解决这些线程不安全的问题，可以采用 HashTable、Collections.synchronizedMap、以及ConcurrentHashMap 来实现线程安全的 Map 类。具体原理是：

* HashTable 是直接在操作方法上加 synchronized 关键字，锁住整个数组，粒度比较大。
* Collections.synchronizedMap 是使用 Collections 集合工具的内部类，通过传入 Map 封装出一个 SynchronizedMap 对象，内部定义了一个对象锁，方法内通过对象锁实现。
* ConcurrentHashMap 使用分段锁，降低了锁粒度，使并发度大大提高。

其中，ConcurrentHashMap 成员变量使用 volatile 修饰，避免了指令重排序，同时保证内存可见性。另外使用CAS 操作和 synchronized 结合实现赋值操作，多线程操作只会锁住当前操作索引的节点。