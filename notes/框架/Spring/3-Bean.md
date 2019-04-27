

# 一、Bean 的配置项

Spring 中所有的对象都称为 Bean。

- Id：IOC 容器中 Bean 的唯一标识
- Class：具体要实例化的类 
- Scope：作用域
- Constructor arguments：构造器参数
- Properties：属性
- Autowiring mode：自动装配模式
- lazy-initialization mode：懒加载模式
- Initializaton/destruction mothod：初始化和销毁方法

# 二、Bean 的作用域

- singleton：单例，默认方式，指一个 Bean 容器中只存在一份。
- prototype：每次请求（每次使用）创建新的实例，destroy 方式不生效。
- request：每次 http 请求创建一个实例且仅在当前 request 内有效。
- session：每次 http 请求创建，在当前 session 内有效。
- global session：基于 protlet 的 web 中有效，如果是在 web 中，同 session。

```xml
<bean id="test" class="com.str818.bean.test" scope="singleton"></bean>
```

# 三、Bean 的声明周期

