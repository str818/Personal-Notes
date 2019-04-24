* [一、对象导论](#一对象导论)
* [二、数据类型](#二数据类型)
* [三、操作符](#三操作符)
* [正则表达式](#正则表达式)
* [反射](#反射)
* [参考](#参考)


# 一、对象导论

### 面向对象三大基本特性

#### 1、封装

把客观事物封装成抽象的类，并且类可以把自己的数据和方法只让可信的类或者对象操作，对不可信的进行信息隐藏。

#### 2、继承

让某个类型的对象获得另一个类型的对象的属性的方法。

#### 3、多态

一个类实例的相同方法在不同情形有不同表现形式。


# 二、数据类型

### 基本类型

若类的某个成员是基本数据类型，即时没有进行初始化，Java 也会确保其有一个默认值。

| 基本类型 |   大小   |  最小值   |      最大值      | 包装器类型 |     默认值     |
| :------: | :------: | :-------: | :--------------: | :--------: | :------------: |
| boolean  |    —     |     —     |        —         |  Boolean   |     false      |
|   char   |  16-bit  | Unicode 0 | Unicode 2^16 - 1 | Character  | '\u0000'(null) |
|   byte   |  8 bits  |   -128    |       +127       |    Byte    |    (byte)0     |
|  short   | 16 bites |   -2^15   |    +2^15 - 1     |   Short    |    (short)0    |
|   int    | 32 bits  |   -2^31   |    -2^31 - 1     |  Integer   |       0        |
|   long   | 64 bits  |   -2^63   |    +2^62 - 1     |    Long    |       0L       |
|  float   | 32 bits  |  IEEE754  |     IEEE754      |   Float    |      0.0f      |
|  double  | 64 bits  |  IEEE754  |     IEEE754      |   Double   |      0.0d      |
|   void   |    —     |     —     |        —         |    Void    |       —        |

# 三、操作符

### 优先级

当一个表达式存在多个操作符时，操作符的优先级就决定了各部分的计算顺序。Java 对计算顺序做了特别的规定，其中，最简单的规则就是先乘除后加减。程序员经常会忘记其他优先级规则，所以应该用括号明确规定计算顺序。

### 算术运算符

Java 的**算数操作符**与其它大多数程序设计语言都是相同的，其中包括 `加号(+)`、`减号(-)`、`乘号(*)`、`除号(/)`以及`取模(%)`。整数的除法将会去掉小数位而不是四舍五入。

**一元加、减操作符**：`x = -a;`

**自增、自减操作符**

前缀式：先加减，后运算  `++a; --b;` 

后缀式：先运算，后加减  `a++; b--;`

**除法**
```java
10 / 0          // 运行报错
10.0 / 0        // Infinity（正无穷）
-10.0 / 0       // -Infinity（负无穷）
0.0 / 0         // NaN
```

**取模**
```java
15 % 0          //运行报错
15.0 % 0        // NaN
```

### 关系运算符

生成布尔值作为结果，计算操作数值之间的关系。包括 `小于(<)`、`大于(>)`、`小于等于(<=)`、`大于等于(>=)`、`等于(==)` 以及 `不等于(!=)` 。

### 逻辑运算符

`与(&&)`、`或(||)` 和 `非(！)` 能根据参数的逻辑关系，生成一个布尔值。

**短路**

当使用逻辑操作符时，会遇到一种「短路」现象，一旦能够明确无误地确定整个表达式的值，就不再计算表达式余下部分。

### 按位操作符

用来操作整数基本数据类型中单个「比特」（bit），即二进制位。按位操作符会对两个参数中对应的位执行布尔代数运算，并最终生成一个结果。包括 `按位与(&)`、`按位或(|)`、`异或(^)`、`按位非/取反(~)` 。

### 移位操作符

移位运算操作符的运算对象也是二进制的「位」，其只可用来处理整数类型。

* 左移位操作符（`<<`）：能够按照操作符右侧指定的位数将操作数向左移动（低位补 0 ）。
* 「有符号」右移位操作符（`>>`）：将操作数向右移动，使用「符号扩展」：若符号为正，在高位补 0 ；若符号为负，在高位补 1 。
* 「无符号」右移位操作符（`>>>`）：使用「零扩展」，无论正负，高位补 0 。

 对 char、byte、short 类型的数值进行移位处理时，移位之前会被转成 int 。

### 三元操作符

`boolean-exp ? value0 : value1` 若 `boolean-exp` 为 `true` 则计算 `value0` ;若为 `false` 则计算 `value0` 。

### 直接常量

```java
// 十六进制
int i1 = 0x2f;
int i2 = 0x2F;

// 八进制
int i3 = 0117;

// long
long n1 = 200L;
long n2 = 200l;

// float
float f1 = 1F;
float f2 = 1f;

// double
double d1 = 1d;
double d2 = 1D;

// 指数记数法
float exp = 1.29e-42f; // e 表示 10, 1.29 × 10^(-42)
```

# 正则表达式

正则表达式是一种强大而灵活的文本处理工具，使用正则表达式能够以编程的方式构造复杂的文本模式，并对输入的字符串进行搜索，简单来说，正则表达式就是以某种方式来描述字符串。

[正则表达式在线工具](https://regexr.com/)

## 基本语法

|                 语法                 |                           语法解释                           |
| :----------------------------------: | :----------------------------------------------------------: |
| 普通字符（字母、数字、汉字、下划线） |       一个普通字符在表达式中只匹配与之相同的一个字符。       |
|            `\t`、`\n`、`\t`、`\f`            |             表示回车符、换行符、制表符、换页符。             |
|              `[` 与 `]`              | 使用 `[]` 括起来的为一个可选字符组，表示其中包含的一系列字符中任意一个字符。例如 `[abc]` 表示 `a、b、c` 中的任意一个字符； `[a-z]` 表示字符 `a` 到字符 `b` 中的任意一个字符，其中 `-` 表示一个范围。 |
|             `[^` 与 `]`             | 表示一个可选字符组的补集。例如 `[^abc]` 表示 `a、b、c` 之外的任意一个字符。 |
|                  `. `                  |               表示除换行符以外的任意一个字符。               |
|               `\d` 与 `\D `              | `\d` 表示 `0-9` 之间的任意一个字符；`\D` 表示 `0-9` 之外的任意一个字符。 |
|               `\s` 与 `\S`               | `\s` 表示包括空格、制表符、换页符等空白字符的其中任意一个字符；`\S` 表示空白字符以外的任意一个字符。 |
|               `\w` 与 `\W`               | `\w` 表示字母、数字、下划线中的任意一个字符；`\W` 表示字母、数字、下划线之外的任意一个字符。 |
|                  `^`                   |        该符号不匹配任何字符，其匹配字符串开始的地方。        |
|                 ` $ `                  |        该符号不匹配任何字符，其匹配字符串结束的地方。        |
|               `\b `与 `\B `              | 不匹配任何字符，`\b` 表示单词的边界；`\B` 表示非单词边界的地方。 |
|                  `X?`                  |                表示` X `可以出现 `0` 次或 `1` 次。                 |
|                  `X+ `                 |      表示 `X` 至少可以出现 `1` 次，即可以出现 `1` 次或多次。       |
|                 `X{n}`                 |                    表示 `X` 可以出现` n `次。                    |
|                `X{m,n}`                |            表示 `X` 至少出现 `m` 次，最多出现 `n` 次。             |
|                `X{n,}`                 |                    表示 `X` 至少出现 `n` 次。                    |
|                  `?`                   | 该符号只能用在 `?、+、*、{m,n}、{n,}` 的后边，用来修饰可以出现的次数按非贪婪模式进行匹配，即按照匹配模式进行最小限度的匹配。 |
|                  `|`                  | 用来连接两个表达式，表示或的关系。例如 `X\|Y `表示 `X` 或 `Y` 中的任意字符。 |
|                 `()`                  | 将表达式的某个部分作为一个单元，即作一个分组，这样可以对该组整个进行操作。 |
|         `\n`（`n` 表示一个数字）         | 有分组的情况下，表示对分组的引用。例如 `\1` 表示对分组 `1` 的引用。 |
|                  `\`                   | 该字符为转义字符，当一个符号自身有意义而又要表示这个字符的时候，需要在该字符前加一个转义字符 `\` ，例如 `\^` 表示 `^` ，`\$` 表示 `$`，`\\` 表示 `\` 。需要用到转义字符表示的字符还有 `(`、`)`、`[`、`]`、`{`、`}`、`?`、`+`、`*`、`|`。 

### 1、边界符

在进行匹配的时候，边界符不会匹配任何字符，只用于限定边界。

* `^` 与 `$` 分别表示的是字符串开始与结束的位置，若要求匹配的是整个字符串的内容，而不是字符串中的一部分，此时便可以使用 `^` 与 `$` 加以说明。例如，`^a*$` 表示的是由任意个 `a` 组成的字符串，而 `a*` 可以表示匹配整个字符串或其子串。
* `\b` 表示的是单词的边缘，在要求匹配的内容是整个单词，而不是单词的一部分时，`\b` 就派上用场了。例如，`\bTom\b` 对 `Tommy Tom` 进行匹配，只会匹配后面的单词 `Tom` ，而不会匹配 `Tommy` 中的 `Tom` ，而如果不使用 `\b` 则两个都可以匹配。

### 2、匹配次数的贪婪与非贪婪

贪婪与非贪婪模式是针对量词而言的，其修饰符应该放在量词的后面。在匹配有重复字符或组出现的情况下，贪婪与非贪婪模式有不同的含义。

* 贪婪模式下在进行匹配时，将按照最大限度的可能性进行匹配。
* 非贪婪模式在匹配时，按照最小限度的可能性进行匹配。

例如，对于字符串 `abbbabbba` ，若使用正则式 `a[\w]+a` 进行匹配，则会按照贪婪模式进行匹配，匹配到的内容为 `abbbabbba` ，而使用正则式 `a[\w]+?a` 进行匹配，会以非贪婪模式进行匹配，匹配到的内容为 `abbba`。

### 3、分组

实际应用中，有时需要重复出现的不是单个字符，而是特定的一组字符，例如要匹配由任意个 `ab` 组成的字符串，这时就需要使用分组。

* `()` 为分组操作符，其功能为将特定的一组字符作为一个原子（不可分）的匹配单元来看待。例如，`(ab)*` 表示匹配由任意个 `ab` 组成的字符串或子串。
* `\n` ( `n` 表示一个数字) 表示对分组的引用，当用于匹配的正则式中有很多分组时可以使用其对分组进行引用。例如，对于分组正则式 `((23)(45))` ，其中包括三个分组 `2345`、`23`、`45`，编号分别为 `1`、`2`、`3` ，`((23)(45))\1` 表示对 `23452345` 匹配，`((23)(45))\2` 表示对 `234523` 进行匹配。

### 4、示例

日期匹配 `xxxx-xx-xx` ：`^\d{4}-(0[1-9]|1[0-2])-([0-2][1-9]|3[0-1])` 

## String 正则处理方法

在 Java 中有四个内置的运行正则表达式的方法，分别是 `matches()`、`split()`、`replaceFirst()`、`replaceALL()` 。注意 `replace()` 方法不支持正则表达式。

|                  方法                  |                 描述                  |
| :------------------------------------: | :-----------------------------------: |
|           `s.matches("regex")`           | 当且仅当正则匹配整个字符串时返回 `true` |
|            `s.split("regex")`           |     按匹配的正则表达式切片字符串      |
| `s.replaceFirst("regex", "replacement")` |       替换首次匹配的字符串片段        |
|  `s.replaceAll("regex", "replacement")`  |          替换所有匹配的字符           |

## Java 正则匹配

Java 中使用正则表达式需要用到两个类，分别为 `java.util.regex.Pattern` 和 `java.util.regex.Matcher` 。

1.  通过正则表达式创建模式对象 `Pattern` 。
2. 通过模式对象 `Pattern` ，根据指定字符串创建匹配对象 `Matcher` 。
3. 通过匹配对象 `Matcher` ，根据正则表达式操作字符串。

```java
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

    public static void main(String[] args) {
        String text = "Hello Regex!";

        Pattern pattern = Pattern.compile("\\w+");
        
        Matcher matcher = pattern.matcher(text);
        // 遍例所有匹配的序列
        while (matcher.find()) {
            System.out.print("Start index: " + matcher.start());
            System.out.print(" End index: " + matcher.end() + " ");
            System.out.println(matcher.group());
        }
        // 创建第两个模式，将空格替换为 tab
        Pattern replace = Pattern.compile("\\s+");
        Matcher matcher2 = replace.matcher(text);
        System.out.println(matcher2.replaceAll("\t"));
    }
}
```

运行结果：

```java
Start index: 0 End index: 5 Hello
Start index: 6 End index: 11 Regex
Hello    Regex!
```

# 反射

反射（Reflection）是 Java 程序开发语言的特征之一，它允许运行中的 Java 程序获取自身的信息，并且可以操作类或对象的内部属性。通过 Class 获取 class 信息称之为反射。

简而言之，通过反射，可以在运行时获得程序或程序集中每一个类型的成员和成员的信息。

## 一、Class 类

在面向对象的世界里，万事万物皆对象。

Java 语言中只有静态的成员和普通数据类型不是对象，其中静态的成员不是某一个类的，它是属于类的，普通数据类型有其对应的包装类。

同样，类也是对象，类是 `java.lang.Class` 类的实例对象。

### 1、Class 实例对象的表示方法

```java
class Foo{}
Foo foo = new Foo();
```

任何一个类都是 Class 的实例对象，这个实例对象有三种表示方式。

#### 第一种表示方式

任何一个类都有一个隐含的静态成员变量 class。

```java
Class c1 = Foo.class;
```
#### 第二种表示方式

已经知道该类的对象通过 getClass 方法。

```java
Class c2 = foo.getClass();
```

> c1 与 c2 表示了 Foo 类的类类型（class type）。

c1 和 c2 都代表了 Foo 类的类类型，一个类只可能是 Class 类的一个实例对象。

```java
System.out.println(c1 == c2); // true
```

#### 第三种表示方式

```java
Class c3 = null;
try{
    c3 = Class.forName("com.reflect.Foo");
}catch(ClassNotFoundException e){
    e.printStackTrace();
}
```

```java
System.out.println(c2 == c3);
```

可以通过类的类类型创建该类的对象实例：

```java
try{
    Foo foo = (Foo)c1.newInstance(); // 需要有无参的构造器
}catch(InstantiationException e){
    e.printStackTrace();
}catch(IllegalAccessException e){
    e.printStackTrace();
}
```
基本的数据类型、void 关键字等都存在类类型。


### 2、加载 Class 类

#### 静态加载类

在编译时刻就需要加载所有的可能使用到的类。

```java
Circle s = new Circle();
```

#### 动态加载类

```java
// 在运行时刻加载
Class c = Class.forName(args[0]);
// 通过类类型，创建该类对象
Shape s = (Circle)c.newInstance();
s.draw();
```

### 3、Class 类的基本 API 操作

打印类的信息，包括类的成员函数、成员变量。

```java
public static void printClassMessage(Object obj){
    // 要获取类的信息 首先要获取类的类类型
    Class c = obj.getClass(); // 传递的是哪个子类的对象 c 就是该子类的类类型

    // 获取类的名称
    System.out.println("类的名称是：" + c.getName());

    /* Method 类，方法对象
     * 一个成员方法就是一个 Method 对象
     * getMethods() 方法获取的是所有的 public 函数，包括父类继承而来的
     * getDeclaredMethods() 获取的是所有该类自己生命的方法，不问访问权限
     */
    Method[] ms = c.getMethods();//c.getDeclaredMethods()
    for(int i = 0; i < ms.length; i++){
        // 得到方法的返回类型的类类型
        Class returnType = ms[i].getReturnType();
        System.out.print(returnType.getName()+"");

        // 得到方法的名称
        System.out.print(ms[i].getName()+"(");

        // 获取参数类型 --> 得到的是参数列表的类型的类类型
        for(Class class1 : parameTypes){
            System.out.print(class1.getName()+",");
        }

        System.out.println(")");

        /*
         * 成员变量也是对象
         * java.lang.reflect.Field 类封装了关于变量的操作
         * getFields() 方法获取的是所有 public 的成员变量的信息
         * getDeclaredFields() 获取的是该类自己声明的成员变量信息
         */

        Field[] fs = c.getDeclaredFields();// c.getFields();

        for(Field field : fs){
            // 得到成员变量的类型的类类型
            Class fieldType = field.getType();
            String typeName = fieldType.getName();

            // 得到成员变量的名称
            String fieldName = field.getName();
            System.out.println(typeName + " " + fieldName);
        }
    }

    // 构造函数信息 java.lang.Constructor
    Constructor[] cs = c.getConstructors();


}
```


# 参考

* Bruce Eckel. Java 编程思想[M]. 机械工业出版社, 2007.
* 吴亚峰. JavaSE 6.0 编程指南[M]. 人民邮电出版社, 2007.
* [Java 正则表达式详解](https://segmentfault.com/a/1190000009162306)