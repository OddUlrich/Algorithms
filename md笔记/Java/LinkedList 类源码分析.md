# LinkedList 类源码分析

与数组一样，LinkedList 也是一种线性的数据结构，但它不像数组或者 ArrayList 那样在连续的内存空间上存储元素，而是通过引用相互链接，这也成为索引存储。

LinkedList 之中的每一个元素都可以称之为节点（Node），每一个节点包括三个属性：

* 元素本身的值。
* 指向下一个节点的引用地址。
* 指向上一个节点的引用地址。

Node 是 LinkedList 类的一个私有静态内部类，其源码如下：

```java
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

LinkedList 对象的第一个节点没有前一个节点，因此 prev 为 null；最后一个节点没有下一个节点，因此 next 为 null。再加上 LinkedList 记录了头节点 first 和尾结点 last，因此它实际上是一个已知头尾节点的双向链表结构。

```java
public class LinkedList<E>
    extends AbstractSequentialList<E>
    implements List<E>, Deque<E>, Cloneable, java.io.Serializable
{
    transient Node<E> first;
    transient Node<E> last;
}
```



那既然已经有 ArrayList 可以用来处理数组形式的数据了，为什么还需要有 LinkedList 这样一个集合类呢？两者对比有下面几点区别：

|      | ArrayList                                                    | LinkedList                                                   |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 大小 | 数组大小是固定的，即便 ArrayList 可以自动扩容，但依然会有一定的限制。如果声明的容量太小，则需要扩容；如果声明容量过大，又会造成内存的浪费。 | 允许内存动态分配，内存是由编译器在运行时完成的，无需在声明时指定大小。 |
| 地址 | 需要连续的内存位置来存储元素，这是导致添加或者删除元素时成本很高的主要原因。 | 不需要在连续位置上存储元素。元素之间通过引用指定相互之间的位置，因为每个元素的内存位置可以分散。 |



ArrayList 类的设计逻辑如下：

![LinkedList](D:\3.Workplace\Algorithms\md笔记\Java\LinkedList.JPG)

* LinkedList 是一个继承自 AbstractSequentialList 的双向链表，因此它也可以被当做堆栈、队列或者双端队列进行操作。
* LinkedList 实现了 List 接口，所以能对它进行队列操作。
* LinkedList 实现了 Deque 接口，所以能够将它当做双端队列使用。



## 构建函数

创建一个 LinkedList 的实例对象，需要指定 ArrayList 中元素的种类，主要有以下两种方式：

* 不指定参数直接创建一个对象，不需要指定容量大小。
* 传入一个集合类，将其内部所有元素转化成 ArrayList 的元素形式保存。

```java
public LinkedList() {}

public LinkedList(Collection<? extends E> c) {
    this();
    addAll(c);
}
```



## add 方法

```java
public boolean add(E e) {
    linkLast(e);
    return true;
}
void linkLast(E e) {
    final Node<E> l = last;
    final Node<E> newNode = new Node<>(l, e, null);
    last = newNode;
    if (l == null)
        first = newNode;
    else
        l.next = newNode;
    size++;
    modCount++;
}
```

add（）方法会调用 linkLast（）方法，根据入参创建一个新的节点，然后更新当前的 last 节点。如果原链表为空，则新节点同时为 first 节点。从末尾插入还可以使用 addLast（）。同样也可以从链表头部添加新节点，对应的有 addFirst（）。如果要指定插入的位置，可以用 add（int index，E element）。

* offer（）方法直接返回 add（）方法的结果。
* push（）方法直接返回 addFirst（）方法的结果。



## set 方法

LinkedList 使用 set（）方法来更改元素，需要提供下标和新元素的值。

```java
public E set(int index, E element) {
    checkElementIndex(index);
    Node<E> x = node(index);
    E oldVal = x.item;
    x.item = element;
    return oldVal;
}
Node<E> node(int index) {
    // assert isElementIndex(index);

    if (index < (size >> 1)) {
        Node<E> x = first;
        for (int i = 0; i < index; i++)
            x = x.next;
        return x;
    } else {
        Node<E> x = last;
        for (int i = size - 1; i > index; i--)
            x = x.prev;
        return x;
    }
}
```

首先检查指定的下标是否在越界，然后调用 Node 类的 node 方法进行进一步判断后获取下标。这里用到了一个很有用的技巧：**初步判断下标位置，如果靠近末端，则从后往前遍历；如果靠近表头，则从前往后遍历。这样能够节省不少遍历的时间**。找到节点后，用新值替代并返回旧值。



## remove 方法

```java
public boolean remove(Object o) {
    if (o == null) {
        for (Node<E> x = first; x != null; x = x.next) {
            if (x.item == null) {
                unlink(x);
                return true;
            }
        }
    } else {
        for (Node<E> x = first; x != null; x = x.next) {
            if (o.equals(x.item)) {
                unlink(x);
                return true;
            }
        }
    }
    return false;
}
E unlink(Node<E> x) {
    // assert x != null;
    final E element = x.item;
    final Node<E> next = x.next;
    final Node<E> prev = x.prev;

    if (prev == null) {
        first = next;
    } else {
        prev.next = next;
        x.prev = null;
    }

    if (next == null) {
        last = prev;
    } else {
        next.prev = prev;
        x.next = null;
    }

    x.item = null;
    size--;
    modCount++;
    return element;
}
```

首先，在 remove（）方法中，判断 null 要用 ==，否则要用 equals（）。remove（）可以指定元素对象，方法内部调用了 unlink（）方法对待删除节点的前后节点进行更新，分别考虑了前一个节点和后一个节点为空的情况。

* removeFirstOccurrence（）指定删除的对象，与 remove（）相同。
* removeLastOccurrence（）从后往前遍历，删除指定的对象。

* removeFirst（）、removeLast（）方法删除头节点或者尾结点。remove（）没有入参是，内部调用 removeFirst（）。
* pop（）方法直接返回 removeFirst（）的结果。



## 查找元素

```java
public int indexOf(Object o) {
    int index = 0;
    if (o == null) {
        for (Node<E> x = first; x != null; x = x.next) {
            if (x.item == null)
                return index;
            index++;
        }
    } else {
        for (Node<E> x = first; x != null; x = x.next) {
            if (o.equals(x.item))
                return index;
            index++;
        }
    }
    return -1;
}
```

同样地，判断 null 要 ==，否则要用 equals（）。正序查找元素可以使用 indexOf（）方法，倒序查找则可以使用 lastIndexOf（）。基本思路与 ArrayList 相似。除此之外，LinkedList 还提供了其他方法用作查找元素：

* element（）相当于 getFirst（）。

* getFirst（）用于获取第一个元素；getLast（）用于获取最后一个元素。
* poll（）和 pollFirst（）用于删除并返回第一个元素（方法体相同）。
* pollLast（）删除并返回最后一个元素。
* peek（）和 peekFirst（）返回第一个元素但不删除它。
* peekLast（）返回最后一个元素但不删除它。

当我们将 LinkedList 用作队列时，常常使用 poll（）和 peek（）来获取元素内容，offer（）相当于 add（）。

LInkedLIst 查找元素需要遍历，所以时间复杂度为 O(n)。



## 与其他数据结构的联系

* 栈
    * 使用 push（e）、pop（）、peek（）等方法。
    * 等价于 addFirst（e）、getFirst（）+ removeFirst（）、getFirst（）方法。
* 队列
    * 使用 offer（e）、poll（）、peek（）等方法。
    * 等价于 add（e）、getFirst（）+ remove（）、getFirst（）方法。
* 链表
    * 使用 add（e）、remove（）、element（）等方法。
    * 等价于 add（e）、removeFirst（）、getFirst（）方法。



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

