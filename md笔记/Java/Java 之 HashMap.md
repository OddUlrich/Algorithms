# Java 之 HashMap



**Map 接口**实现类的**创建对象**以及对象类型包含的**方法**。

**Map 属于接口类型**，不可以new的方式创建对象。

**SortedMap 属于接口类型**，不可以new的方式创建对象。

**HashMap **基于哈希表实现 Map接口的类，**并允许 null 的值和 null 键**。

**TreeMap**通过红黑树实现 Map 接口的类，key 不可以为 null，会报 **NullPointerException** 异常, value 可以为null。

关于在不同 Map 集合类中，key 和 value 能否为 null 的问题：

| Map 类                 | 类型              | key           | value       |
| ---------------------- | ----------------- | ------------- | ----------- |
| Map                    | 接口              |               |             |
| ConcurrentMap          | 接口              |               |             |
| SortedMap              | 接口              |               |             |
| NavigableMap           | 接口              |               |             |
| HashMap                | 实现 Map 接口的类 | 允许为 null   | 允许为 null |
| TreeMap （基于 RB 树） | 实现 Map 接口的类 | 不允许为 null | 允许为 null |
| LinkedHashMap          |                   |               |             |
| ConcurrentHashMap      |                   |               |             |
| ConcurrentSkipListMap  |                   |               |             |
|                        |                   |               |             |
|                        |                   |               |             |





## values（）

数据类型？







## HashMap 与 HashTable 的区别

