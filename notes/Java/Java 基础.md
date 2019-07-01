

# 一、环境

## JDK 和 JRE 有什么区别？

- JDK：Java Development Kit 的简称，Java 开发工具包，提供了 Java 的开发环境和运行环境。
- JRE：Java Runtime Environment 的简称，Java 运行环境，为 Java 的运行提供了所需环境。

具体来说 JDK 其实包含了 JRE，同时还包含了编译 Java 源码的编译器 Javac，还包含了很多 Java 程序调试和分析的工具。简单来说：如果你需要运行 Java 程序，只需安装 JRE 就可以了，如果你需要编写 Java 程序，需要安装 JDK。

# 二、数据类型

## Java 有几种基本数据类型，各占用多少字节？

- byte/8
- char/16
- short/16
- int/32
- float/32
- long/64
- double/64
- boolean/~

boolean 只有两个值：true、false，可以使用 1 bit 来存储，但是具体大小没有明确规定。JVM 会在编译时期将 boolean 类型的数据转换为 int，使用 1 来表示 true，0 表示 false。JVM 支持 boolean 数组，但是是通过读写 byte 数组来实现的。

## Integer.valueOf(s) 和 Integer.parseInt(s) 的区别？

- `Integer.valueOf(s)` 的返回值是 Integer 类型。当整数值在 -128 ~ 127 之间时，会返回缓存中的对象；若不在这个区间，则创建一个 Integer 并返回。
- `Integer.parseInt(s)` 的返回值是 int 基本类型。

```java
Integer.valueOf("127") == Integer.valueOf("127")      // True，返回缓冲池中相同的对象
Integer.valueOf("128") == Integer.valueOf("128")      // False，创建两个新对象，地址不同
Integer.parseInt("128") == Integer.valueOf("128")     // True，Integer 对象自动拆箱，实际上是两个 int 的基本类型比较
128 == Integer.valueOf("128")                         // True，和前一个是一样的
```

## 什么是自动拆装箱？

自动装箱和拆箱就是基本类型和引用类型之间的转换，至于为什么要转换，因为基本类型转换为引用类型后，就可以 new 对象。

# 三、面向对象

## 什么是面向对象？

面向对象是一种思想，世间万物都可以看做一个对象。

面向对象软件开发具有以下优点：代码开发模块化，更易维护和修改；代码复用性强；增强代码的可靠性和灵活性；增加代码的可读性。

## 面向对象的基本特性？

封装：封装可以使类具有独立性和隔离性，保证类的高内聚。只暴露给类外部或者子类必须的属性和操作。类封装的实现依赖类的修饰符（public、protected 和 private 等）。

继承：对现有类的一种复用机制。一个类如果继承现有的类，则这个类将拥有被继承类的所有非私有特性（属性和操作）。这里指的继承包含：类的继承和接口的实现。

多态：多态是在继承的基础上实现的。多态的三个要素：继承、重写和父类引用指向子类对象。父类引用指向不同的子类对象时，调用相同的方法，呈现出不同的行为，就是类多态特性。多态可以分成编译时多态和运行时多态。

# 四、运算

## & 与 && 的区别？

& 运算符有两种用法：(1) 按位与，(2) 逻辑与。&& 运算符是短路与运算。

&& 之所以称为短路运算是因为，如果 && 左边的表达式的值是 false，右边的表达式会被直接短路掉，不会进行运算。

## 什么是值传递和引用传递？

值传递是对基本型变量而言的，传递的是该变量的一个副本，改变副本不影响原变量。

引用传递一般是对于对象型变量而言的，传递的是该对象地址的一个副本，并不是原对象本身。一般认为，Java 内的传递都是值传递，Java 中实例对象的传递是引用传递。

# 五、继承

## Java 支持多继承么？

Java 中类不支持多继承，只支持单继承（即一个类只有一个父类）。 但是 Java 中的接口支持多继承，即一个子接口可以有多个父接口。（接口的作用是用来扩展对象的功能，一个子接口继承多个父接口，说明子接口扩展了多个功能，当类实现接口时，类就扩展了相应的功能）。

# 普通类和抽象类的区别？

- 普通类不能包含抽象方法，抽象类可以包含抽象方法。
- 抽象类不能直接实例化，普通类可以直接实例化。

## 接口和抽象类的区别？

从设计层面来说，抽象类是对类的抽象，是一种模板设计，接口是行为的抽象，是一种行为规范。

实现：抽象类的子类使用 extends 来继承；接口必须使用 implements 来实现接口。
构造函数：抽象类可以有构造函数；接口不能有。
实现数量：类可以实现很多个接口；但是只能继承一个抽象类。
访问修饰符：接口中的方法默认使用 public 修饰；抽象类中的方法可以是任意访问修饰符。

##  

# 六、String

## String 属于基础的数据类型吗？

不属于。

## Java 中操作字符串都有哪些类？

操作字符串的类有：String、StringBuffer、StringBuilder。

String 和 StringBuffer、StringBuilder 的区别在于 String 声明的是不可变的对象，每次操作都会生成新的 String 对象，然后将指针指向新的 String 对象，而 StringBuffer、StringBuilder 可以在原有对象的基础上进行操作，所以在经常改变字符串内容的情况下最好不要使用 String。

StringBuffer 和 StringBuilder 最大的区别在于，StringBuffer 是线程安全的，而 StringBuilder 是非线程安全的，但 StringBuilder 的性能却高于 StringBuffer，所以在单线程环境下推荐使用 StringBuilder，多线程环境下推荐使用 StringBuffer。

## String str="i" 与 String str=new String("i") 一样吗？

不一样，因为内存的分配方式不一样。String str="i"的方式，Java 虚拟机会将其分配到常量池中；而 String str=new String("i") 则会被分到堆内存中。

## 如何将字符串反转？

使用 StringBuilder 或者 stringBuffer 的 reverse() 方法。

## String 类的常用方法都有那些？

- indexOf()：返回指定字符的索引。
- charAt()：返回指定索引处的字符。
- replace()：字符串替换。
- trim()：去除字符串两端空白。
- split()：分割字符串，返回一个分割后的字符串数组。
- getBytes()：返回字符串的 byte 类型数组。
- length()：返回字符串长度。
- toLowerCase()：将字符串转成小写字母。
- toUpperCase()：将字符串转成大写字符。
- substring()：截取字符串。
- equals()：字符串比较。

# 七、Object

## == 和 equals 的区别？

== 对于基本类型来说是值比较，对于引用类型来说是比较的是引用；而 equals 默认情况下是引用比较，只是很多类重新了 equals 方法，比如 String、Integer 等把它变成了值比较，所以一般情况下 equals 比较的是值是否相等。

## 两个对象的 hashCode() 相同，则 equals() 也一定为 true，对吗？

不对，两个对象的 hashCode() 相同，equals() 不一定 true。

代码示例：

```java
String str1 = "通话";
String str2 = "重地";
System. out. println(String. format("str1：%d | str2：%d",  str1. hashCode(),str2. hashCode()));
System. out. println(str1. equals(str2));
```

执行的结果：

```java
str1：1179395 | str2：1179395

false
```

代码解读：很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode() 相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。



# 参考

[32 道常见的 Java 基础面试题](http://www.ituring.com.cn/article/507089)

