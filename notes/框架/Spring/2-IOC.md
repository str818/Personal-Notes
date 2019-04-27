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

# 二、什么是 IOC

IOC：控制反转，控制权的转移，应用程序本身不负责依赖对象的创建和维护，而是由外部容器负责创建和维护。也就是说获得依赖对象的过程被反转了。

**DI（依赖注入）** 是 IOC 的一种实现方式。

<div align="center">  <img src="img/IoC.png" width="60%"/> </div><br>

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

```
FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext("F:/workspace/appcontext.xml");
```

### II. Classpath

```
ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-context.xml);
```

### III. Web 应用

```
<listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
<servlet>
    <servlet-name>context</servlet-name>
    <servlet-class>org.springframework.web.context.ContextLoaderServlet</servlet.class>
    <load-on-startup>1</load-on-startup>
</servlet>
```

# 四、Spring 注入

Spring 注入是指在启动 Spring 容器加载 bean 配置的时候，完成对变量的赋值行为。

## 1. Setter 注入

```java
public class TestA {
    private TestB myTestB;
    public void setMyTestB(TestB myTestB) {
        this.myTestB = myTestB;
    }
}
```

xml 配置文件。

```
<bean id="testA" class="TestA">
    <property name="myTestB">
        <ref bean="testB" />
    </property>
</bean>
<bean id="testB" class="TestB"></bean>
```

## 2. 构造注入

```java
public class TestA {
    private TestB myTestB;
    public TestA(TestB myTestB) {
        this.myTestB = myTestB;
    }
}
```

xml 配置文件。

```
<bean id="testA" class="TestA">
    <constructor-arg>
        <bean class="TestB" />
    </constructor-arg>
</bean>
```



