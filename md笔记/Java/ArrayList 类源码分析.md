# ArrayList 类源码分析

数组是 Java 编程中最常用的基本数据类型之一，但其本身自带的方法不多，不便于进行相对复杂的操作。因此在 Java 中有一个相对应的集合类 ArrayList，可以称得上在集合方面最常用的类了。ArrayList 类的设计逻辑如下：

![](D:\3.Workplace\Algorithms\md笔记\Java\ArrayList.JPG)

ArrayList 实现了 List 接口，其内在逻辑是基于数组实现的。对于数组这个基本数据类型来说，其大小在声明的时候就已经是固定了的，不能再进行动态的调整。如果依次向数组里添加数据，一旦数组满了，就不能再添加任何元素了。相比而言，ArrayList 是数组的一个很好的替代方案，它提供了比数组更加丰富的预定义方法（包括增删改查），并且其大小是可以根据元素的多少进行自动调整，非常灵活。



## 构建函数

创建一个 ArrayList 的实例对象，主要有以下几种方式。不管使用哪一种方式，都需要指定 ArrayList 中元素的种类，而且不能是基本数据类型（如 int、float、boolean 等）。简单总结，就是包括这样的几类：

* 创建时指定初始大小作为入参。这样可以有效避免在添加元素时进行不必要的扩容。但通常情况下，我们很难确认元素的个数，因此一般不指定初始大小。
* 不指定参数直接创建，引用一个预创建的空数组对象。
* 传入一个集合类，将其内部所有元素转化成 ArrayList 的元素形式保存。

```java
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable
{
    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                                               initialCapacity);
        }
    }
    public ArrayList() {this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;}
    
    public ArrayList(Collection<? extends E> c) {
        elementData = c.toArray();
        if ((size = elementData.length) != 0) {
            if (elementData.getClass() != Object[].class)
                elementData = Arrays.copyOf(elementData, size, Object[].class);
        } else {
            // replace with empty array.
            this.elementData = EMPTY_ELEMENTDATA;
        }
    }
}
```



## add 方法

在 Java 8 之前，不传入参数进行 ArrayList 初始化时，代码会默认将 ArrayList 的容量设置为 10，而从 Java 8 开始，初始创建 ArrayList 时默认将容量设为 0，直至有新元素添加时，才将其容量设定为 10。这样做的目的是，为了节省内存消耗。如果在实时 Java 应用程序中创建了数百万个 ArrayList 对象，全部默认容量为 10 就意味着我们在创建时为每个底层数组分配 10 个指针 （40 或者 80 个字节），并且用控制填充它们。这样的空数组会占用大量内存。当有新元素添加时，才进行初始化，这个过程可以称之为**延迟初始化**。延迟初始化可以推迟以上的内存消耗，直到我们的程序实际使用对应的 ArrayList 对象为止。

```java
private static final int DEFAULT_CAPACITY = 10;
private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
// Integer.MAX_VALUE = 0x7fffffff;

public boolean add(E e) {
    ensureCapacityInternal(size + 1);
    elementData[size++] = e;
    return true;
}

private void ensureCapacityInternal(int minCapacity) {
    if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
        minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
    }

    ensureExplicitCapacity(minCapacity);
}

private void ensureExplicitCapacity(int minCapacity) {
    modCount++;
    if (minCapacity - elementData.length > 0)
        grow(minCapacity);
}

private void grow(int minCapacity) {
    int oldCapacity = elementData.length;
    int newCapacity = oldCapacity + (oldCapacity >> 1);

    if (newCapacity - minCapacity < 0)
        newCapacity = minCapacity;

    if (newCapacity - MAX_ARRAY_SIZE > 0)
        newCapacity = hugeCapacity(minCapacity);

    elementData = Arrays.copyOf(elementData, newCapacity);
}

private static int hugeCapacity(int minCapacity) {
    if (minCapacity < 0) 
        throw new OutOfMemoryError();
    return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
}
```

通过 add 方法添加新的元素，如果不指定下标的话，就默认将新元素添加到 ArrayList 对象的末尾。在实际执行过程中，如果数组列表的容量已满，则内部会通过一系列逻辑来实现扩容，具体过程体现在上述的源码中。

* 首先检查数组内部容量，不满 10 则扩容为默认的大小 10；否则扩容为当前大小加 1。
* 调用 grow（）方法进行扩容，新数组容量原定为旧数组容量的 1.5 倍。但如果扩容 1.5 倍仍不能满足最小需要的容量大小，则新容量就确定为最小需要容量的值。
* 如果上面得到的新容量大于 MAX_ARRAY_SIZE，则需要通过 hugeCapacity（）方法进一步判断：
    * 如果最小需要容量大于 MAX_ARRAY_SIZE，则将 Integer.MAX_VALUE 作为新数组的大小；
    * 否则，将 MAX_ARRAY_SIZE 作为新数组的大小。

之所以选择每次扩容 1.5 倍的容量，是因为考虑到要使得每次扩容后允许添加的新元素数量增加，这样可以减少频繁的进行扩容操作。



调用 add 方法添加元素还可以指定插入下标位置，实现插入后，该位置后面的所有元素都依次向后移动一个位置，内部调用 System.arraycopy（）方法，时间复杂度为 O(n)，频繁移动元素可能会导致效率问题，特别是集合中元素数量较多时。因此在日常开发中，我们应当尽量避免在大集合中使用该插入方法。相对而言，直接添加到末尾 add（）的时间复杂度则为 O(1)。



## remove 方法

ArrayList 的 remove 方法主要有两种：

* 删除指定下标位置上的元素。
* 删除指定值的元素。但如果存在多个相同的元素，只会删除第一个出现的元素。

两者都是先获取删除的目标在 ArrayList 中的位置或者值，前者返回要删除的元素，后者通过break label 的方式找到要删除的元素下标位置。随后统一都是通过 fastRemove 执行具体的删除操作。

```java
public E remove(int index) {
    Objects.checkIndex(index, size);
    final Object[] es = elementData;

    @SuppressWarnings("unchecked") E oldValue = (E) es[index];
    fastRemove(es, index);

    return oldValue;
}
public boolean remove(Object o) {
    final Object[] es = elementData;
    final int size = this.size;
    int i = 0;
    found: {
        if (o == null) {
            for (; i < size; i++)
                if (es[i] == null)
                    break found;
        } else {
            for (; i < size; i++)
                if (o.equals(es[i]))
                    break found;
        }
        return false;
    }
    fastRemove(es, i);
    return true;
}

private void fastRemove(Object[] es, int i) {
    modCount++;
    final int newSize;
    if ((newSize = size - 1) > i)
        System.arraycopy(es, i + 1, es, i, newSize - i);
    es[size = newSize] = null;
}

public void clear() {
    modCount++;
    final Object[] es = elementData;
    for (int to = size, i = size = 0; i < to; i++)
        es[i] = null;
}
```

fastRemove 的源码实现过程：先判断被删除的元素下标是否为末尾，如果是则不需要复制数组，直接将末尾元素赋值为 null 即可；否则则调用 System.arraycopy（）方法复制数组，将被删除元素后面的所有元素向前移，并把最后一位置为 null。使用 clear（）删除所有元素时，数组容量不变，仅把所有位置置为 null。

需要注意的是，被删除元素为 null 时要用 == 判断，非 null 时就用 equals（）方法判断，因为 equals（）方法不是 null 安全的。

删除一个元素需要遍历列表，因此时间复杂度为 O(n)。



## 查找元素

查找元素同样也是分为两类：

* 正序查找：indexof（）
* 倒序查找：lastIndexOf（）

```java
public int indexOf(Object o) {
    return indexOfRange(o, 0, size);
}

int indexOfRange(Object o, int start, int end) {
    Object[] es = elementData;
    if (o == null) {
        for (int i = start; i < end; i++) {
            if (es[i] == null) {
                return i;
            }
        }
    } else {
        for (int i = start; i < end; i++) {
            if (o.equals(es[i])) {
                return i;
            }
        }
    }
    return -1;
}
```

两种方法的实现过程基本相同，lastIndexOf（）方法遍历的时候从后往前进行。同样的，当元素为 null 时使用 == 进行判断，否则使用 equals（）方法。结果返回所要查找元素的下标位置，否则返回 -1。

ArrayList 中的 contains（）方法可以判断列表中是否包含某个元素，其内部就是调用了 indexOf（）方法实现。

```java
public boolean contains(Object o) {
	return indexOf(o) >= 0;
}
```



如果 ArrayList 中的元素时有序的，那么使用二分查找法，效率更高。我们也可以通过调用 Collections 类的 sort（）方法对 ArrayList 进行排序，对于数值型列表会默认按从小到大进行排列，对于 String 类型会默认按照字典序排列。如果是自定义类型的列表，可以通过重写 ArrayList 中的 Comparator 进行排序。

```java
Collections.sort();
int index = Collections.binarySearch(list, obj);
```



* 访问 ArrayList 中的一个元素可以直接通过其下标进行查找，因此时间复杂度为 O(1)。
* 在一个未排序的列表中执行查找操作的时间复杂度为 O(n)。
* 在一个有序的列表中执行二分查找，其时间复杂度为 O(log n)。



## ArrayList v.s LinkedList

| 方法                        | ArrayList                                      | LinkedList                                                   |
| --------------------------- | ---------------------------------------------- | ------------------------------------------------------------ |
| get（int index）            | O(1)                                           | O(n) - 理论上为 O(n/2)<br />getFirst（）和getLast（）为 O(1) |
| add（E e）                  | 不扩容：O(1)<br />扩容：取决于 Arrays.copy（） | O(1)                                                         |
| add（int index，E element） | O(n) - System.arraycopy（）必然执行            | O(n) - node（index）进行遍历<br />addFirst（）和 addLast（）为 O(1) |
| remove（int index）         | O(n) - System.arraycopy（）必然执行            | O(n) - node（index）进行遍历                                 |

需要注意以下几点：

* 如果列表规模很大，ArrayList 占用的内存在声明时已经确定了，未使用的位置可以直接用 null 填充；而 LinkedList 的每个元素有更多开销，因为要存储上一个和下一个元素的地址。
* ArrayList 只能用作列表；LinkedList 可以用作列表或者队列、栈等，因为它实现了 Deque 接口。
* 如果不清楚使用 ArrayList 还是 LinkedList，就选择 ArrayList 吧，整体来说效率更高。

