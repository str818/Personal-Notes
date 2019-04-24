## 一、简介

javascript 可以嵌入到 html 中，是基于对象和**事件驱动**的脚本语言。能够动态修改、增加、删除 html 和 css 代码，能够动态校验用户输入的表单数据。

## 二、基本语法

### 引入方式

##### 1、内嵌脚本

```html
<body>
    <input type="button" value="按钮" onclick="alert('hello')">
<body>
```

##### 2、内部脚本

```html
<script type="text/javascript">
    alert("hello");
</script>
```

##### 3、外部脚本

创建一个 js 文件，文件名称为 ```hello.js``` ，内容为：

```javascript
alert("hello");
```

在 html 中链接外部脚本：

```html
<script type="text/javascript" src="hello.js"></script>
```

### 变量

javascript 中的变量均使用 var 进行声明

```javascript
var a = 0;
var b = true;
var c = "hello";	//单引号与双引号都可以修饰字符串
```

也可以直接进行声明

```javascript
a = 10;
```

var 可以修饰所有类型，可以动态改变类型。

```javascript
var a = 10;
a = "str818";
```

### 数据类型

##### 1、基本数据类型

| 数字类型 | 字符串类型 | 布尔类型 | 空类型 |  未定义   |
| :------: | :--------: | :------: | :----: | :-------: |
|  number  |   string   | boolean  |  null  | underfind |

##### 2、类型转换

```javascript
var a = 10;													//数字类型
var b = "818str818";										//字符串类型
var c = true;												//布尔类型

alert(typeof a.toString());									//string
alert(parseInt(c));											//NaN
alert(parseInt(b));											//818，将前面的数字提取出来
```

### 运算符

##### 1、赋值运算符

```javascript
var x = 818;
```

##### 2、算数运算符

```javascript
‘+’ ‘-’ ‘*’ ‘/’ ‘%’
‘+’: 与Java相同，遇到字符串变为连接符
‘-’ ‘*’ ‘/’：都是先将字符串转为数字后再进行运算
```

##### 3、逻辑运算符

```javascript
&& ||
```

##### 4、比较运算符

```javascript
<	>	>=	<=	!=	==

===: 全等，判断类型与值是否都相等

var a = 10;
var b = "10";
alert(a == b);		//true
alert(a === b);		//false
```

##### 5、三元运算符

```javascript
var a = 818 > 817 ? "大于" : "小于";
```

##### 6、void运算符

没有返回值的意思，常用在链接标签中，点击后不会进行跳转。

```javascript
<a herf="javascript:void(0);">点击</a>
```

7、类型运算符

```javascript
typeof : 返回变量的数据类型
instanceof : 判断变量是否是某种数据类型
```

### 逻辑语句

```javascript
//if else
//数字非0 字符串非空也是true
if(){}
else{}

//switch
switch(x){
	case : ""
        break;
    default:
}

//for
for(int i = 0;i < 10; i++){
}

//for in
var array = [8,1,8];
for(index in array){//index为数组下标
    alert(arr[index]);
}
```

## 三、内建对象

### 1、Number

```javascript
创建方式：
	var num=new Number(10);
	var num=Number(10);
属性和方法：
	toString()：转成字符串
	valueOf()：返回一个 Number 对象的基本数字值
```

### 2、Boolean

```javascript
创建方式：
    var boo = new Boolean(true);    
    var boo = Boolean(false);
属性和方法：
    toString():转成字符串
    valueOf()：返回一个 Boolean 对象的基本值(boolean)
```

### 3、String

```javascript
创建方式：
    var str = new String("str818");
    var str = String("str818");
属性和方法：
    length:字符串的长度
    charAt():返回索引字符
    charCodeAt:返回索引字符unicode
    indexOf():返回字符的索引
    lastIndexOf();字符串中该字符最后出现的索引号
    split();将字符串按照特殊字符切割成数组
    substr():从起始索引号提取字符串中指定数目的字符
    substring():提取字符串中两个指定的索引号之间的字符
    toUpperCase();转大写
```

### 4、Array

```javascript
创建方式：
    var arr = new Array();//空数组
    var arr = new Array(10);//创建一个长度为10的数组
    var arr = new Array(ele0, ele1, ..., elen);//创建数组直接实例化元素
    var arr = [];//空数组
    var arr = [9,8,5,"java"];//创建数组直接实例化元素
属性和方法：
    length:数组长度
    join()：把数组的所有元素放入一个字符串。元素通过指定的分隔符进行分隔一个
    pop():删除并返回最后元素
    push()：向数组的末尾添加一个或更多元素，并返回新的长度
    reverse();反转数组
    sort();排序
```

### 5、Data

```javascript
创建方式：    
    var d = new Date();
    var d = new Date(毫秒值);//代表从1970-1-1到现在的一个毫秒值
属性和方法
    getFullYear():年
    getMonth():月 0-11
    getDate():日 1-31
    getDay()：星期 0-6
    getTime():返回1970年1月1日午夜到指定日期（字符串）的毫秒数
    toLocaleString();获得本地时间格式的字符串
```

### 6、Math

```javascript
创建方式：    
    使用Math时无需创建对象直接使用即可，例如：Math.PI
    其实Math中的方法就像Java中的静态方法，可以通过类名.方法名的方式使用
属性和方法
    PI：圆周率
    abs():绝对值
    ceil():对数进行上舍入
    floor():对数进行下舍入
    pow(x,y)：返回 x 的 y 次幂
    random():0-1之间的随机数
    round():四舍五入
```

### 7、RegExp

```javascript
创建方式：    
    var reg = new RegExp(正则);
    var reg = /^正则规则$/;
规则的写法：
    [0-9] 
    [A-Z]
    [a-z]
    [A-z]
    \d    数字
    \D    非数字
    \w    查找单词字符
    \W    查找非单词字符
    \s    查找空白字符
    \S    查找非空白字符
    n+    出现至少一次
    n*    出现0次或多次
    n?    出现0次或1次
    {5} 出现5
    {2,8} 2到8次
方法：    
    test(str):检索字符串中指定的值。返回 true 或 false
```

## 四、函数

### 定义方式

##### 1、普通方式

```javascript
function method(){
    alert("str818");
}
method();
```

##### 2、匿名方式

```javascript
var method = function(){
    alert("str818");
}
method();
```

##### 3、对象函数

```javascript
var fn = new Function("a","b","alert(a+b)");
fn(8,8);
```

### 参数

可以不用写形参的参数，形参和实参个数不一定相等

```javascript
function fn(a,b){
    //arguments是一个内置的数组对象，会将传递的所有实参封装到里面
    for(var i = 0;i<arguments.length;i++){
        alert(arguments[i]);
    }
}
```

### 返回值

有返回值就直接 return 即可，没有 return 就没有返回值。

```javascript
function fn(a,b){
    return a * b;
}
```

### 全局函数

##### 1、编码解码

下面三种方法编码的符号范围不同，实际开发中常使用第一种。

| 编码方式 | encodeURI() | encodeURIComponent() |  escape()  |
| :------: | :---------: | :------------------: | :--------: |
| 解码方式 | decodeURI() | decodeURIComponent() | unescape() |

##### 2、强制转换

```javascript
Number()
String()
Boolean()
//转成数字
parseInt()
parseFloat()
```

##### 3、eval 函数

这一函数能够将字符串当做 javascript 脚本运行。

## 五、事件

用户点击 html 页面中的按钮，这时会发生一个**事件**，叫做点击事件，被点击的按钮叫做**事件源**，点击按钮后弹出一个对话框，点击按钮后发生的行为叫做**响应行为**。

### javascript 常用事件

- onclick:点击事件
- onchange:域内容被改变的事件
- onfoucus:获得焦点的事件
- onblur:失去焦点的事件
- onmouseover:鼠标悬浮的事件
- onmouseout:鼠标离开的事件
- onload:加载完毕的事件

