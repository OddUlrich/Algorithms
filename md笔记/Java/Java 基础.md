# Java 基础





## 基本数据类型的底层

### equals（）

Float 类和 Double 类都重写了相应的 equals 方法，在比较之前会先判断是否同属于 Float 类或者 Double 类。如果不是则直接返回 false，如果是，再继续比较数值大小。

JDK 1.8 的源码如下：

* Float类重写了equals方法：

```java
public boolean equals(Object obj) {
    return (obj instanceof Float)
        	&& (floatToIntBits(((Float)obj).value) == floatToIntBits(value));
}
```

* Double类重写了equals方法：

```java
public boolean equals(Object obj) {
    return (obj instanceof Double)
        	&& (doubleToIntBits(((Float)obj).value) == doubleToIntBits(value));
}
```



### Java 中的 hash

HashMap中使用的是分离链表法。hashset 中调用 hashmap 来存储数据的，hashmap 采用的链地址法：当哈希槽中有其他对象了，使用链表的方式连接到那个对象上。

ThreadLocalMap 中使用**开放地址法**来处理散列冲突，之所以采用不同的方式主要是因为：在 ThreadLocalMap 中的散列值分散得十分均匀，很少会出现冲突。并且ThreadLocalMap 经常需要清除无用的对象，使用纯数组更加方便。

ThreadLocalMap 使用开放地址法 - 线性探测法：当前哈希槽有其他对象占了，顺着数组索引寻找下一个，直到找到为止。ThreadLocalMap 通过 key（ThreadLocal 类型）的hashcode来计算数组存储的索引位置 i。如果 i 位置已经存储了对象，那么就往后挪一个位置依次类推，直到找到空的位置，再将对象存放。另外，在最后还需要判断一下当前的存储的对象个数是否已经超出了阈值（threshold 的值）大小，如果超出了，需要重新扩充并将所有的对象重新计算位置。







## 对象监听器 Listener

* MouseMotionListener
* FocusListener
* WindowsListerner
* ActionListener



## 对象序列化

用 ObjectOutputStream 和 ObjectInputStream 可以将对象进行传输（Object 开头的类）。



对象序列化的所属类需要实现 Serializable 接口。

声明为 static 和 transient 类型的成员数据不会被序列化。因为 static 代表类的状态， transient 代表对象的临时数据。transient 修饰的变量在对象串化的时侯并不会将所赋值的值保存到传中，串化的对象从磁盘读取出来仍然是null。



## hibernate核心接口

原理：
1.读取并解析配置文件
2.读取并解析映射信息，创建SessionFactory
3.打开Sesssion
4.创建事务Transation
5.持久化操作
6.提交事务
7.关闭Session
8.关闭SesstionFactory
为什么要用：
\1. 对JDBC访问数据库的代码做了封装，大大简化了数据访问层繁琐的重复性代码。
\2. Hibernate是一个基于JDBC的主流持久化框架，是一个优秀的ORM实现。他很大程度的简化DAO层的编码工作
\3. hibernate使用Java反射机制，而不是字节码增强程序来实现透明性。
\4. hibernate的性能非常好，因为它是个轻量级框架。映射的灵活性很出色。它支持各种关系数据库，从一对一到多对多的各种复杂关系。



## ServletConfig 接口

![img](https://uploadfiles.nowcoder.com/images/20171024/9430388_1508834386231_FE8B1A979ADF6E3C2C114AF3F9CA693C)

![img](http://uploadfiles.nowcoder.com/images/20151008/141075_1444315881150_E11C84B33E70541AB97E9F4CC37B0C18)

GenericServlet类的实现接口中包括了ServletConfig接口，但是它自身的init(ServletConfig config)方法又需要外界给它传递一个实现ServletConfig的对象，就是说GenericServlet和ServletConfig的依赖关系既是继承关系，也是一种关联关系。



下面的方法可用在 Servlet 程序中读取 HTTP 头。这些方法通过 *HttpServletRequest* 对象可用：

* Cookie[] getCookies()：返回一个数组，包含客户端发送该请求的所有的 Cookie 对象。
* Object getAttribute(String name)：以对象形式返回已命名属性的值，如果没有给定名称的属性存在，则返回 null。
* String getHeader(String name)：以字符串形式返回指定的请求头的值。Cookie也是头的一种。
* String getParameter(String name)：以字符串形式返回请求参数的值，或者如果参数不存在则返回 null。