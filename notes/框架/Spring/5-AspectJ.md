# 一、什么是 AspectJ

AspectJ 是一个基于 Java 语言的 AOP 框架。

Spring 2.0 以后新增了对 AspectJ 切点表达式的支持。

@AspectJ 是 AspectJ 1.5 的新增功能，通过 JDK5 注解技术，允许直接在 Bean 类定义切面。

新版本 Spring 框架，建议使用 ApsectJ 方式来开发 AOP。

# 二、注解开发

## 1. 通知类型

- @Before 前置通知，相当于 BeforeAdvice
- @AfterReturning 后置通知，相当于 AfterReturningAdvice
- @Around 环绕通知，相当于 MethodInterceptor
- @AfterThrowing 异常抛出通知，相当于 ThrowAdvice
- @After 最终 final 通知，不管是否异常，该通知都会执行

## 2. 定义切点

通过 execution 函数，可以定义切点的方法切入。

语法：execution(<访问修饰符>?<返回类型><方法名>(<参数>)<异常>)

例如：

- 匹配所有类 public 方法 execution(public * * (..))
- 匹配指定包下所有类方法
    - 不包含子包 execution(* com.str818.dao.*(..)) 
    - 包含子包下的所有类 execution(* com.str818.dao..*(..))
- 匹配指定类的所有方法 execution(* com.str818.service.UserService.*(..))
- 匹配实现特定接口的所有类方法 execution(* com.str818.dao.GenericDAO+.*(..))
- 匹配所有 save 开头的方法 execution(* save*(..))

## 3. 使用案例

```java
public class ProductDao {

    public void save(){
        System.out.println("保存商品...");
    }

    public String update(){
        System.out.println("修改商品...");
        return "hello";
    }

    public void delete(){
        System.out.println("删除商品...");
    }

    public void findOne(){
        System.out.println("查询一个商品...");
    }

    public void findAll(){
        System.out.println("查询所有商品...");
    }
}
```

```java
@Aspect
public class MyAspectAnno {

    @Before(value = "execution(* com.str818.aspectJ.demo1.ProductDao.*(..))")
    public void before(){
        System.out.println("==前置通知==");
    }
}
```

```xml
<!--开启AspectJ的注解开发，自动代理-->
<aop:aspectj-autoproxy/>

<!--目标类-->
<bean id="productDao" class="com.str818.aspectJ.demo1.ProductDao"/>

<!--定义切面-->
<bean class="com.str818.aspectJ.demo1.MyAspectAnno"/>
```

测试类。
```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringDemo1 {

    @Resource(name = "productDao")
    private ProductDao productDao;

    @Test
    public void demo1(){
        productDao.save();
        productDao.update();
        productDao.delete();
        productDao.findAll();
        productDao.findOne();
    }
}
```

## 4. 前置通知 @Before

可以在方法中传入 JoinPoint 对象，用来获得切点信息。

```java
@Before(value = "execution(* com.str818.aspectJ.demo1.ProductDao.save(..))")
public void before(JoinPoint joinPoint){
    System.out.println("==前置通知==" + joinPoint);
}
```

## 5. 后置通知 @AfterReturing

可以通过 returning 属性获取方法的返回值。

```java
@AfterReturning(value="execution(* com.str818.aspectJ.demo1.ProductDao.update(..)",returning = "result")
public void afterReturing(Object result){
    System.out.println("==后置通知==" + result);
}
```

## 6. 环绕通知 @Around

around 方法的返回值就是目标代理方法执行的返回值，ProceedingJoinPoint 可以调用拦截目标方法执行，如果不调用 ProceedingJoinPoint 的 proceed 方法，目标方法就会被拦截。

```java
@Around(value="execution(* com.str818.aspectJ.demo1.ProductDao.delete(..)")
public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    System.out.println("==环绕前通知==");
    Object obj = joinPoint.proceed(); // 执行目标方法
    System.out.println("==环绕后通知==");
    return obj;
}
```

## 7. 异常抛出通知 @AfterThrowing

通过设置 throwing 属性，可以设置发生异常的对象参数。

```java
@AfterThrowing(value="execution(* com.str818.aspectJ.demo1.ProductDao.delete(..)",throwing = "e")
public void afterThrowing(Throwable e){
    System.out.println("==异常抛出通知==" + e.getMessage());
}
```

## 8. 最终通知 @After

无论是否出现异常，最终通知总是会被执行。

```java
@After(value="execution(* com.str818.aspectJ.demo1.ProductDao.delete(..)")
public void after(){
    System.out.println("==最终通知==");
}
```

## 9. 切点命名 @Pointcut

在每个通知内定义切点，会造成工作量大，不易维护，对于重复的切点，可以使用 @Pointcut 进行定义。

切点方法：private void 无参方法，方法名为切点名。

当通知多个切点时，可以使用 || 进行连接。


```java
@Before(value="myPointcut()")
public void before(JoinPoint joinPoint){
    System.out.println("==前置通知==" + joinPoint);
}
@Pointcut(value="execution(* com.str818.aspectJ.demo1.ProductDao.save(..))")
private void myPointcut(){}
```

# 三、XML 开发

接口。
```java
public interface CustomerDao {
    public void save();
    public String update();
    public void delete();
    public void findOne();
    public void findAll();
}
```

实现类。
```java
public class CustomerDaoImpl implements CustomerDao {
    public void save() {
        System.out.println("保存客户...");
    }

    public String update() {
        System.out.println("修改客户...");
        return "spring";
    }

    public void delete() {
        System.out.println("删除客户...");
    }

    public void findOne() {
        System.out.println("查询一个客户...");
    }

    public void findAll() {
        System.out.println("查询多个客户...");
    }
}
```

切面。
```java
public class MyAspectXml {

    // 前置通知
    public void before(JoinPoint joinPoint){
        System.out.println("==XML方式的前置通知==" + joinPoint);
    }

    // 后置通知
    public void afterReturing(Object result){
        System.out.println("==XML方式的后置通知==" + result);
    }

    // 环绕通知
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("==XML方式的环绕前通知==");
        Object obj = joinPoint.proceed();
        System.out.println("==XML方式的环绕后通知==");
        return obj;
    }

    // 异常抛出通知
    public void afterThrowing(Throwable e){
        System.out.println("==XML方式的异常抛出通知==" + e.getMessage());
    }

    // 最终通知
    public void after(){
        System.out.println("==XML方式的最终通知==");
    }
}
```

配置文件。
```xml
<!--==配置目标类==-->
<bean id="customerDao" class="com.str818.aspectJ.demo2.CustomerDaoImpl"/>

<!--配置切面类-->
<bean id="myAspectXml" class="com.str818.aspectJ.demo2.MyAspectXml"/>

<!--==aop的相关配置==-->
<aop:config>
    <!--配置切入点-->
    <aop:pointcut id="pointcut1" expression="execution(* com.str818.aspectJ.demo2.CustomerDao.save(..))"/>
    <aop:pointcut id="pointcut2" expression="execution(* com.str818.aspectJ.demo2.CustomerDao.update(..))"/>
    <aop:pointcut id="pointcut3" expression="execution(* com.str818.aspectJ.demo2.CustomerDao.delete(..))"/>
    <aop:pointcut id="pointcut4" expression="execution(* com.str818.aspectJ.demo2.CustomerDao.findOne(..))"/>
    <aop:pointcut id="pointcut5" expression="execution(* com.str818.aspectJ.demo2.CustomerDao.findAll(..))"/>
    <!--配置AOP的切面-->
    <aop:aspect ref="myAspectXml">
        <!--配置前置通知-->
        <aop:before method="before" pointcut-ref="pointcut1"/>
        <!--配置后置通知-->
        <aop:after-returning method="afterReturing" pointcut-ref="pointcut2" returning="result"/>
        <!--配置环绕通知-->
        <aop:around method="around" pointcut-ref="pointcut3"/>
        <!--配置异常抛出通知-->
        <aop:after-throwing method="afterThrowing" pointcut-ref="pointcut4" throwing="e"/>
        <!--配置最终通知-->
        <aop:after method="after" pointcut-ref="pointcut5"/>
    </aop:aspect>
</aop:config>
```

测试类。
```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="classpath:applicationContext2.xml")
public class SpringDemo2 {

    @Resource(name="customerDao")
    private CustomerDao customerDao;

    @Test
    public void demo1(){
        customerDao.save();
        customerDao.update();
        customerDao.delete();
        customerDao.findOne();
        customerDao.findAll();
    }
}
```