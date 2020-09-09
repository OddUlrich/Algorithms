# Java 函数速查表



## Scanner

Scanner in = new Scanner(System.in)

* hasNextLine()、nextLine()
* hasNext()、next()
* hasNextInt()、nextInt()
* hasNextLong()、nextLong()

**注意**：判断是否仍有输入，与其后实际进行读取的格式必须要一致，即上述方法必须配对使用。 使用 nextLong() 主要考虑到数值过大，用 nextInt() 容易导致数据溢出。

|          | next() | nextInt() | nextLine() |
| -------- | ------ | --------- | ---------- |
| 特点     |        |           |            |
| 使用场景 |        |           |            |





## Integer

Integer.parseInt(str,16)  // 将字符串按照十六进制解析成整型数（十进制）。

Integer.decode(str)  // 内部识别符号、基数说明符、字符序列。



## String - 最后一个元素索引为 length() - 1

* length( )
* charAt(int index)
* split("\\\s+")  // 按照空格分离。
* split("\\\\\\\\\")  // 按照一个反斜杠分离。



String 是用关键字 final 修饰的，创建的变量会被放入字符串常量池中。当再次创建同样的字符串时，发现常量池中有则直接使用。此时两个字符串可以用 == 比较，结果为 true。



## StringBuffer

* StringBuffer s = new StringBuffer(x);  // x 为初始化容量长度。
* length( )
* capacity( ) // buffer 的容量。
* charAt(int index)
* delete(int start, int end)
* deleteCharAt(int index)



## HashSet <Boxing Type>

* boolean add(E val)



## HashMap

* get(E key, E val)
* put(E key)
* getOrDefault(E key, E default_val)
* remove(E key)
* containsKey(E key)
* keySet( )





## Stack <Boxing Type>

* empty( ) 
* peek( )
* pop( )
* push(Object element)
* search(Object element)





## Queue & Deque <Boxing Type>

| 功能               | Queue        | Deque            |
| ------------------ | ------------ | ---------------- |
| 添加元素到队尾     | add (E e)    | addLast (E e)    |
| 取队首元素并删除   | E remove ()  | E removeFirst () |
| 取队首元素但不删除 | E element () | E getFirst ()    |
| 添加元素到队首     | -            | addFirst (E e)   |
| 取队尾元素并删除   | -            | E removeLast ()  |
| 取队尾元素但不删除 | -            | E getLast ()     |



* offer(E val)
* poll( )
* 



## PriorityQueue<Boxing Type>

* poll()
* offer(Object)
* size()
* peek()

不指定 Comparator 时默认为最小堆。重写 Comparator 就能实现大顶堆。

```java
PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
    @Override
    public int compare(Integer o1, Integer o2) {
        return o2.compareTo(o1);  // 或者 return o2 - o1;
    }
});
```





## Arrays

* sort(Array a)
* fill(Array a, E val)
* copyOfRange(Array a, int start, int end)
* binarySearch(Array a, E val)