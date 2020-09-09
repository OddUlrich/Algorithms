# Java 中的 String、StringBuilder 和 StringBuffer 类

在实际开发环境中，对字符串对象的操作时无法避免的，我们常用的与字符串相关的对象主要包括 String、StringBuilder 和 StringBuffer 三种，本文就它们的特点、性能，以及经常讨论的观点进行讨论。

要理解他们的区别之前，先看看这么一个常用的例子：

```java
public class Main {
     public static void main(String[] args) {
         String str1 = "hello world";
         String str2 = new String( "hello world" );
         String str3 = "hello world";
         String str4 = new String( "hello world" );
         System.out.println(str1==str2);	// 返回 false。
         System.out.println(str1==str3);	// 返回 true。
         System.out.println(str2==str4);	// 返回 false。
     }
}
```

这里涉及到 JVM 内存机制的实现，在 class 文件中有一部分是用来存储编译期间生成的字面常量和符号引用，这部分叫做 class 文件常量池，在运行期间对应着方法区的运行时常量池。

在上述代码中，String str1 = "hello world" 以及 String str3 = "hello world" 都在编译期间生成该字面常量，并存储了一份在运行时常量池。通过这种方式来实现 String 对象与引用的绑定，JVM 执行引擎会先在运行时常量池查找是否存在相同的字面常量，如果存在，则直接将引用指向已经存在的字面常量；否则再运行时常量池开辟一个空间来存储该字面常量，并将引用指向该字面常量。因此 str1 和 str3 将指向同一个字面常量，== 运算返回 true。

而通过 new 来创建对象的过程是在堆里进行的，堆区不会去检测该对象是否已经存在，因此通过 new 关键字创建出来的对象一定是不同的对象，即便它们的内容是相同的。



## String 类



我们看看 String 类实现的接口以及声明的成员方法：

```java
public final class String implements java.io.Serializable, Comparable<String>, CharSequence {
    /** The value is used for character storage. */
    @Stable
    private final byte[] value;
    
    /** The identifier of the encoding used to encode the bytes in {@code value}. */
    private final byte coder;
    
    /** Cache the hash code for the string */
    private int hash; // Default to 0
    
    /** use serialVersionUID from JDK 1.0.2 for interoperability */
    private static final long serialVersionUID = -6849794470754667710L;   
}
```

从源码中可以看出 String 类一些特点：

* String 类是 final 类，这意味着 String 类不能被继承，而且它的成员方法都默认为 final 方法。早期的 JVM 版本，被 final 修饰的方法被转为内嵌调用以提升运行效率；而现在的 JVM 版本，不需要再考虑用 final 来提升方法调用效率，只有在确定**不想让方法被覆盖**时，才将方法设置为 final。
* String 类是通过 char 数组来保存字符串的。



<u>其中，对于 UTF16 编码的字符串来说，value 的长度取一般就是该字符串的长度；对于 Latin 字符串，则 value 的长度即为字符串的长度。</u>



这里列出一些常用 String 对象构建方法的源码：

```java
public final class String implements java.io.Serializable, Comparable<String>, CharSequence {
/** 构造函数 */
    public String() {
        this.value = "".value;
        this.coder = "".coder;
    }

    @HotSpotIntrinsicCandidate
    public String(String original) {
        this.value = original.value;
        this.coder = original.coder;
        this.hash = original.hash;
    }

    public String(char value[]) {
        this(value, 0, value.length, null);
    }

    public String(char value[], int offset, int count) {
        this(value, offset, count, rangeCheck(value, offset, count));
    }

    private static Void rangeCheck(char[] value, int offset, int count) {
        checkBoundsOffCount(offset, count, value.length);
        return null;
    }

    public String(StringBuffer buffer) {
        this(buffer.toString());
    }

    public String(StringBuilder builder) {
        this(builder, null);
    }
}
```

常用 String 类成员方法：

```java
public final class String implements java.io.Serializable, Comparable<String>, CharSequence {   
	public int length() {
        return value.length >> coder();
    }
    public boolean isEmpty() {
        return value.length == 0;
    }
    public char charAt(int index) {
        if (isLatin1()) {
            return StringLatin1.charAt(value, index);
        } else {
            return StringUTF16.charAt(value, index);
        }
    }
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof String) {
            String aString = (String)anObject;
            if (coder() == aString.coder()) {
                // 比较长度和每一个字符。
                return isLatin1() ? StringLatin1.equals(value, aString.value)
                    : StringUTF16.equals(value, aString.value);
            }
        }
        return false;
    }

    public int hashCode() {
        int h = hash;
        if (h == 0 && value.length > 0) {
            hash = h = isLatin1() ? StringLatin1.hashCode(value)
                : StringUTF16.hashCode(value);
        }
        return h;
    }
/*************************************************************************/
final class StringLatin1 {
    public static int hashCode(byte[] value) {
        int h = 0;
        for (byte v : value) {
            h = 31 * h + (v & 0xff);
        }
        return h;
    }
}
final class StringUTF16 {
    public static int hashCode(byte[] value) {
        int h = 0;
        int length = value.length >> 1;
        for (int i = 0; i < length; i++) {
            h = 31 * h + getChar(value, i);
        }
        return h;
    }
}
/*************************************************************************/

    public int indexOf(int ch) {
        return indexOf(ch, 0);
    }
    public int lastIndexOf(int ch) {
        return lastIndexOf(ch, length() - 1);
    }
    public int indexOf(String str) {
        if (coder() == str.coder()) {
            return isLatin1() ? StringLatin1.indexOf(value, str.value)
                              : StringUTF16.indexOf(value, str.value);
        }
        if (coder() == LATIN1) {  // str.coder == UTF16
            return -1;
        }
        return StringUTF16.indexOfLatin1(value, str.value);
    }
    public int lastIndexOf(String str) {
        return lastIndexOf(str, length());
    }
    
    public String substring(int beginIndex) {
        if (beginIndex < 0) {
            throw new StringIndexOutOfBoundsException(beginIndex);
        }
        int subLen = length() - beginIndex;
        if (subLen < 0) {
            throw new StringIndexOutOfBoundsException(subLen);
        }
        if (beginIndex == 0) {
            return this;
        }
        return isLatin1() ? StringLatin1.newString(value, beginIndex, subLen)
                          : StringUTF16.newString(value, beginIndex, subLen);
    }    
    public String substring(int beginIndex, int endIndex) {
        int length = length();
        checkBoundsBeginEnd(beginIndex, endIndex, length);
        int subLen = endIndex - beginIndex;
        if (beginIndex == 0 && endIndex == length) {
            return this;
        }
        return isLatin1() ? StringLatin1.newString(value, beginIndex, subLen)
                          : StringUTF16.newString(value, beginIndex, subLen);
    }    
    
    public String replace(char oldChar, char newChar) {
        if (oldChar != newChar) {
            String ret = isLatin1() ? StringLatin1.replace(value, oldChar, newChar)
                                    : StringUTF16.replace(value, oldChar, newChar);
            if (ret != null) {
                return ret;
            }
        }
        return this;
    }
    
    public String strip() {
        String ret = isLatin1() ? StringLatin1.strip(value)
                                : StringUTF16.strip(value);
        return ret == null ? this : ret;
    } 
    public char[] toCharArray() {
        return isLatin1() ? StringLatin1.toChars(value)
                          : StringUTF16.toChars(value);
    }
    
    public static String valueOf(Object obj) {
        return (obj == null) ? "null" : obj.toString();
    }
    public static String valueOf(char data[]) {
        return new String(data);
    }
    public static String copyValueOf(char data[], int offset, int count) {
        return new String(data, offset, count);
    }
    
    public String repeat(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("count is negative: " + count);
        }
        if (count == 1) {
            return this;
        }
        final int len = value.length;
        if (len == 0 || count == 0) {
            return "";
        }
        if (len == 1) {
            final byte[] single = new byte[count];
            Arrays.fill(single, value[0]);
            return new String(single, coder);
        }
        if (Integer.MAX_VALUE / count < len) {
            throw new OutOfMemoryError("Repeating " + len + " bytes String " + count + " times will produce a String exceeding maximum size.");
        }
        final int limit = len * count;
        final byte[] multiple = new byte[limit];
        System.arraycopy(value, 0, multiple, 0, len);
        int copied = len;
        for (; copied < limit - copied; copied <<= 1) {
            System.arraycopy(multiple, 0, multiple, copied, copied);
        }
        System.arraycopy(multiple, 0, multiple, copied, limit - copied);
        return new String(multiple, coder);
    }
    
    public native String intern();
```

以上方法有几个点值得注意：

* equals 方法先判断是否属于同一个对象，否则再判断两者字符串的长度以及每一个字符是否相等。
* indexOf 和 lastIndexOf 方法实质上都是遍历找第一个出现的匹配项，一个从头开始找，一个从尾开始找。获取字符串首次出现的下标时，处理思想是：先找到模式串**第一个字符在目标串出现的位置**，然后遍历从该位置往后对应模式串长度的部分，看是否每一个字符串都相等。
* hashCode 必然去非零值，从 h = 0 开始，按照字符个数依次执行 h = 31 * h + getChar(value, i)。
* substring 内部调用了 Arrays.copyOfRange( ) 并返回结果作为入参，调用 String 的构造函数，返回一个新的 String 对象。
* strip 方法找出首尾第一个非空格的字符，取子串构建新字符串返回即可。
* valueOf 方法返回 Object obj 对象自身的 toString( ) 结果。
* repeat 方法用 count 作为入参，返回一个新的字符串，内容为重复原字符串 count 次。
* intern 方法的使用。在String类中，intern方法是一个本地方法，在运行时常量池中查找是否存在内容相同的字符串，如果存在则返回指向该字符串的引用，如果不存在，则会将该字符串入池，并返回一个指向该字符串的引用。



从上面的一些方法可以看出，基本所有操作都不是在原有的字符串上进行的，而是重新生成了一个新的字符串对象。也就是说进行这些操作后，最原始的字符串并没有被改变。String 类对象的一个最基本的原则就是，**对 String 对象的任何改变都不影响到原对象，相关的任何修改操作都会生成新的对象**。



## 理解三者的区别

如果在我们的实际应用中，常常要对字符串进行修改，从上面 String 类的内部实现可以知道，如果我们只使用 String 类来实现这个过程，必然需要创建大量新的 String 对象，而这些对象如果没有被及时回收，那么会造成很大的内存资源浪费甚至内存泄漏。

StringBuilder 类和 StringBuffer 类常用于需要频繁修改字符串的场景。

当我们通过 String 类实现以下这个代码时：

```java
string += "new string";
```

实际上 JVM 会自动将其优化为：

```java
StringBuilder str = new StringBuilder(string);
str.append("new string");
string = str.toString();
```



其实 StringBuilder 类和 StringBuffer 类拥有的成员属性以及成员方法基本相同主要区别在于 StringBuffer 类的成员方法前面多了一个 **synchronized** 关键字，这个关键字在多线程环境下起到安全保护作用，也就是说 StringBuffer 是线程安全的。





对比三者的性能，直接相加字符串，效率很高，因为在编译器便确定了它的值，也就是说形如 "I"+"love"+"java"; 的字符串相加，在编译期间便被优化成了"Ilovejava"。对于间接相加（即包含字符串引用），形如 s1+s2+s3; 效率要比直接相加低，因为在编译器不会对引用变量进行优化。String、StringBuilder、StringBuffer三者的执行效率相对比较结果为：

> 　　StringBuilder > StringBuffer > String

这三个类是各有利弊，应当根据不同的情况来进行选择使用：

- 当字符串相加操作或者改动较少的情况下，建议使用 String str = "hello" 这种形式；
- 当字符串相加操作较多的情况下，建议使用 StringBuilder，如果采用了多线程，则使用 StringBuffer。



## StringBuilder 类

单线程内频繁修改字符串





## StringBuffer 类

多线程内频繁修改字符串





