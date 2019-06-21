
# JDK 和 JRE 有什么区别？

- JDK：Java Development Kit 的简称，Java 开发工具包，提供了 Java 的开发环境和运行环境。
- JRE：Java Runtime Environment 的简称，Java 运行环境，为 Java 的运行提供了所需环境。

具体来说 JDK 其实包含了 JRE，同时还包含了编译 Java 源码的编译器 Javac，还包含了很多 Java 程序调试和分析的工具。简单来说：如果你需要运行 Java 程序，只需安装 JRE 就可以了，如果你需要编写 Java 程序，需要安装 JDK。

# Integer.valueOf(s) 和 Integer.parseInt(s) 的区别？

- `Integer.valueOf(s)` 的返回值是 Integer 类型。当整数值在 -128 ~ 127 之间时，会返回缓存中的对象；若不在这个区间，则创建一个 Integer 并返回。
- `Integer.parseInt(s)` 的返回值是 int 基本类型。

```java
Integer.valueOf("127") == Integer.valueOf("127")      // True，返回缓冲池中相同的对象
Integer.valueOf("128") == Integer.valueOf("128")      // False，创建两个新对象，地址不同
Integer.parseInt("128") == Integer.valueOf("128")     // True，Integer 对象自动拆箱，实际上是两个 int 的基本类型比较
128 == Integer.valueOf("128")                         // True，和前一个是一样的
```

