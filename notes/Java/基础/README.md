
# Integer.valueOf(s) 和 Integer.parseInt(s)

- `Integer.valueOf(s)` 的返回值是 Integer 类型。当整数值在 -128 ~ 127 之间时，会返回缓存中的对象；若不在这个区间，则创建一个 Integer 并返回。

- `Integer.parseInt(s)` 的返回值是 int 基本类型。


```java
Integer.valueOf("127") == Integer.valueOf("127")      // True，返回缓冲池中相同的对象
Integer.valueOf("128") == Integer.valueOf("128")      // False，创建两个新对象，地址不同
Integer.parseInt("128") == Integer.valueOf("128")     // True，Integer 对象自动拆箱，实际上是两个 int 的基本类型比较
128 == Integer.valueOf("128")                         // True，和前一个是一样的
```

