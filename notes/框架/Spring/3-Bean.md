

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

定义 -> 初始化 -> 使用 -> 销毁

## 1. 初始化

I. 配置 init-method。

```xml
<bean id="exampleInitBean" class="examples.ExampleBean" init-method-"init" />
```

```java
public class ExampleBean {
    public void init() {
        // do something
    }
}
```

II. 实现 org.springframework.beans.factory.InitializingBean 接口，覆盖 afterPropertiesSet() 方法。

```java
public class ExampleInitializingBean implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        // do something
    }
}
```

## 2. 销毁

I. 配置 destroy-method。

```xml
<bean id="exampleInitBean" class="examples.ExampleBean" init-method-"cleanup" />
```

```java
public class ExampleBean {
    public void cleanup() {
        // do something
    }
}
```

II. 实现 org.springframework.beans.factory.DisposableBean 接口，覆盖 destroy() 方法。

```java
public class ExampleDisposableBean implements DisposableBean {
    @Override
    public void destroy() throws Exception {
        // do something
    }
}
```

## 3. 全局配置

在配置文件中设置可选的默认初始化与销毁方法。

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd"
       default-init-method="init" default-destroy-method="destroy">
</beans>
```

# 四、Aware

Spring 中提供了一些以 Aware 结尾的接口，实现了 Aware 接口的 bean 在被初始化之后，可以获取相应资源。

- ApplicationContextAware：能够获取上下文 context。
- BeanNameAware：获取 Bean 的 name。

# 五、Bean 的自动装配

Spring 容器可以在不使用 `<constructor-arg>` 和 `<property>` 元素的情况下自动装配相互协作的 bean 之间的关系。只需要在 `<bean>` 中定义 autowire 属性。

```xml
<bean id="customer" class="com.str818.bean.Customer" autowire="byName" />
```

在Spring中，支持 5 自动装配模式。
	
- no：缺省情况下，自动配置是通过 ref 属性手动设定
- byName：根据属性名称自动装配。如果一个 bean 的名称和其他 bean 属性的名称是一样的，将会自装配它。
- byType：按数据类型自动装配。如果容器中存在一个与指定属性类型相同的 bean，那么将与该属性自动装配；如果存在多个该类型的 bean，那么抛出异常，并指出不能使用 byType 方式进行自动装配。
- constructor：与 byType 方式类似，不同之处在于它应用于构造器参数。
- autodetect：如果找到默认的构造函数，使用 constructor; 否则，使用 byType。

# 六、Resource

在使用 Spring 作为容器进行项目开发中会有很多的配置文件，这些文件都是通过 Spring 的 Resource 接口来实现加载的。

- ClassPathResource：通过类路径获取资源文件
- FileSystemResource：通过文件系统获取资源
- UrlResource：通过URL地址获取资源
- ByteArrayResource：获取字节数组封装的资源
- ServletContextResource：获取ServletContext环境下的资源
- InputStreamResource：获取输入流封装的资源

```java
Resource resource = new ClassPathResource("test.txt");
System.out.println(resource.contentLength());
System.out.println(resource.lastModified());
```

## 七、注解

### @Component

通用注解，可用于任何 bean。

### @Repository

通常用于注解 DAO 类，即持久层。

### @Service

通常用于注解 Service 类，即服务层。

### @Controller

通常用于 Controller 类，即控制层(MVC)。

### @Autowired

自动装配，默认按类型匹配的方式，在容器中查找匹配的 Bean，当有且仅有一个匹配的 Bean 时，Spring 将其注入 @Autowired 标注的变量中。

### @Qualifier

如果容器中有多个匹配的 Bean，可以通过 @Qualifier 注解限定 Bean 的名称。

### @Resource

与 @Autowired 注解的作用类似，但 @Autowired 默认按照 byType 方式进行匹配，而 @Resource 默认感召 byName 方式进行匹配。

@Autowired 是 Spring 的注解；@Resource 是 J2EE 的注解。

### @Configuration 和 @Bean

@Configuration 把一个类作为一个 IoC 容器，它的某个方法头上如果注册了 @Bean，就会作为这个 Spring 容器中的 Bean。


