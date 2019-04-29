
# 一、什么是 AOP

AOP 是 Aspect Oriented Programming 的缩写，意为：面向切面编程，通过预编译方式和运行期动态代理实现程序功能的统一维护的一种技术。

主要功能是：日志记录、性能统计、安全控制、事务处理与异常处理等。

## 1. 切面

<div align="center">  <img src="img/aspect.png" width="40%"/> </div><br>

## 2. 实现方式

- 预编译
  - AspectJ
- 运行期动态代理（JDK 动态代理、CGLib 动态代理）
  - SpringAOP
  - JbossAOP

## 3. 相关概念

|         名称         |                             说明                             |
| :------------------: | :----------------------------------------------------------: |
|    切面 (Aspect)     |       一个关注点的模块化，这个关注点可能会横切多个对象       |
|  连接点 (Joinpoint)  |                 程序执行过程中的某个特定的点                 |
|    通知 (Advice)     |             在切面的某个特定的连接点上执行的动作             |
|  切入点 (Pointcut)   |    匹配连接点的断言，在 AOP 中通知和一个切入点表达式关联     |
| 引入 (Introduction)  |        在不修改类代码的前提下，为类添加新的方法和属性        |
| AOP 代理 (AOP Proxy) |    AOP 框架创建的对象，用来实现切面契约 (aspect contract)    |
|    织入 (Weaving)    | 把切面连接到其它的应用程序类型或者对象上，并创建一个被通知的对象，分为：编译型织入、类加载时织入、执行时织入 |

## 4. Advice 的类型

|                  名称                  |                             说明                             |
| :------------------------------------: | :----------------------------------------------------------: |
|        前置通知 (Before advice)        | 在某连接点 (join point)之前执行的通知，但不能阻止连接点前的执行 (除非它抛出一个异常) |
|  返回后通知 (After returning advice)   |         在某连接点 (joint point)正常完成后执行的通知         |
| 抛出异常后通知 (After throwing advice) |                在方法抛出异常退出时执行的通知                |
|     后通知 (After(finally) advice)     | 当某连接点退出的时候执行的通知 (不论是正常返回还是异常退出)  |
|        环绕通知 (Around adivce)        |                     包围一个连接点的通知                     |

## 5. AOP 的配置

Spring 所有的切面和通知器都必须放在一个 `<aop:config>` 内，每一个 `<aop:config>` 可以包含 pointcut、advisor 和 aspect 元素（必须按照顺序进行声明）。

```xml
<aop:config>
	<aop:aspect id="myAspect" ref="aBean">
        <aop:pointcut id="businessService"
                      expecution="excution(* com.xyz.myapp.service..(..))"/>
        <aop:before mehtod="before" pointcut-ref="businessService"/>
        <aop:after-returning method="afterReturning" pointcut-ref="businessService"/>
        <aop:after-throwing method="afterThrowing" pointcut-ref="businessService"/>
        <aop:after method="after" pointcut-ref="businessService"/>
        <aop:around method="around" pointcut-ref="businessService"/>
    	...
    </aop:aspect>
</aop:config>

<bean id="aBean" class="...">
	...
</bean>
```





# 二、AOP 基本概念

