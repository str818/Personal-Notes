# 一、接口

## 1. 什么是接口？

接口用于沟通的中介物的抽象化，实体把自己提供给外界的一种抽象化说明，用以由内部操作分离出外部沟通方法，使其能被修改内部而不影响外界其他实体与其交互的方式。

## 2. 面向接口编程

结构设计中，分清层次及调用关系，每层只向外（上层）提供一组功能接口，各层之间仅依赖接口而非实现类。

接口实现的变动不影响各层间的调用，这一点在公共服务中尤为重要。

「面向接口编程」中的「接口」是用于隐藏具体实现和实现多态性的组件。

定义接口。

```java
public interface OneInterface {
    String hello(String word);
}
```

接口的实现类。

```java
public class OneInterfaceImpl implements OneInterface {
    @Override
    public String hello(String word){
        return "Word from interface OneInterface : " + word;
    }
}
```

面向接口的使用。

```java
public class Client {
    public static void main(String[] args) {
        OneInterface oif = new OneInterfaceImpl();
        System.out.println(oif.hello("word."));
    }
}
```

# 二、控制反转 - IOC

IOC：控制反转（Inverse of Control），控制权的转移，应用程序本身不负责依赖对象的创建和维护，而是由外部容器负责创建和维护。也就是说获得依赖对象的过程被反转了。简单说，就是创建对象的控制权被反转到了 Spring 框架。

## 1. 传统写法

传统的写法直接创建对象使用，应用程序本身负责对象的创建与维护，对象的创建都写在业务代码中，耦合度很高。

定义接口：
```java
public interface UserService {
    public void sayHello();
}
```

实现类：
```java
public class UserServiceImpl implements UserService{
    public void sayHello() {
        System.out.println("Hello");
    }
}
```

测试代码：
```java
@Test
public void demo1() {
    UserService userService = new UserServiceImpl();
    userService.sayHello();
}
```

## 2. IOC 写法

控制反转是将对象的创建与维护交给外部的容器，通过配置文件与工厂模式创建对象，将对象的创建从逻辑代码中剥离，实现松耦合。

定义配置文件：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="userService" class="com.str818.ioc.UserServiceImpl"></bean>

</beans>
```

测试代码：
```java
@Test
public void demo2() {
    // 创建 Spring 的工厂
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    // 加载实现类
    UserService userService = (UserService) applicationContext.getBean("userService");
    // 调用方法
    userService.sayHello();
}
```

# 三、依赖注入

DI：依赖注入（Dependency Injection）是 IOC 的一种实现方式，在 Spring 创建这个对象的过程中，将这个对象所依赖的属性注入进去。

<div align="center">  <img src="img/IoC.png" width="60%"/> </div><br>

## 1. 传统写法

接着上面的例子，如果要给 UserServiceImpl 对象添加属性，在创建对象之后需要在代码中对属性进行初始化。

UserServiceImpl 类。
```java
public class UserServiceImpl implements UserService{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sayHello() {
        System.out.println("Hello " + name);
    }
}
```

测试类，由于接口没有 name 的属性，所以不能使用接口引用对象了。
```java
@Test
public void demo1() {
    // UserService userService = new UserServiceImpl();
    UserServiceImpl userService = new UserServiceImpl();
    userService.setName("张三");
    userService.sayHello();
}
```

## 2. 依赖注入

依赖注入方法只需要对配置文件更改，不需要修改源代码。
```xml
<bean id="userService" class="com.str818.ioc.UserServiceImpl">
    <property name="name" value="李四"></property>
</bean>
```


# 三、Spring 的 Bean 配置

`spring-ioc.xml` 配置文件。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="test" class="com.str818.bean.Test"></bean>
</beans>
```

待装配的 Bean。

```java
package com.str818.bean;
public class Test {
    public void print(){
        System.out.println("test success!");
    }
}
```

测试类。

```java
public class Client {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-ioc.xml");
        Test test = (Test) context.getBean("test");
        test.print();
    }
}
```

# 三、Bean 容器的初始化

## 1. 基础

- org.springframework.beans
- org.springframework.context
- BeanFactory 提供配置结构和基本功能，加载并初始化 Bean。
- ApplicationContext 保存了 Bean 对象并在 Spring 中被广泛使用。

## 2. 方式

### I. 本地文件

```java
FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext("F:/workspace/appcontext.xml");
```

### II. Classpath

```java
ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-context.xml);
```

### III. Web 应用

```xml
<listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
<servlet>
    <servlet-name>context</servlet-name>
    <servlet-class>org.springframework.web.context.ContextLoaderServlet</servlet.class>
    <load-on-startup>1</load-on-startup>
</servlet>
```



