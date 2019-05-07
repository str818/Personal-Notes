
# 一、什么是 JDBC Template

为了简化持久化操作，Spring 在 JDBC API 之上提供了 JDBC Template 组件。

JDBC Template 提供统一的模板方法，在保留代码灵活性的基础上，尽量减少持久化代码。

JDBC API 方式编写：

```java
Statement statement = conn.createStatement();
ResultSet resultSet = statement.executeQuery("select count(*) COUNT from student");
if(resultSet.next()){
    Integer count = resultSet.getInt("COUNT");
}
```

JDBC Template 方式编写：
```java
Integer count = jt.queryForObject("select count(*) COUNT from student", Integer.class);
```

# 二、配置

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans.xsd
    http://www.springframework.org/schema/context
    http://www.springframework.org/schema/context/spring-context.xsd
    http://www.springframework.org/schema/aop
    http://www.springframework.org/schema/aop/spring-aop.xsd
    http://www.springframework.org/schema/tx
    http://www.springframework.org/schema/tx/spring-tx.xsd">

    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/selection_course?useUnicode=true&amp;characterEncoding=gbk"/>
        <property name="username" value="root"/>
        <property name="password" value="initial123"/>
    </bean>

    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="dataSource"/>
    </bean>
</beans>
```

# 三、基本使用

## 1. 获取 JdbcTemplate

```java
ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
jdbcTemplate = (JdbcTemplate)context.getBean("jdbcTemplate");
```

## 2. execute

```java
jdbcTemplate.execute("create table user1(id int,name varchar(20))");
```

## 3. update 与 batchUpdate

### I. update 方法

对数据进行增删改操作。

1. `int update(String sql, Object[] args)`

```java
String sql = "insert into student(name,sex) values(?,?)";
jdbcTemplate.update(sql,new Object[]{"张飞","男"});
```

2. `int update(String sql, Object... args)`
```java
String sql = "update student set sex=? where id=?";
jdbcTemplate.update(sql,"女",1003);
```

### II. batchUpdate

批量增删改操作。

1. `int[] batchUpdate(String[] sql)`

```java
String[] sqls={
        "insert into student(name,sex) values('关羽','女')",
        "insert into student(name,sex) values('刘备','男')",
        "update student set sex='女' where id=2001"
};
jdbcTemplate.batchUpdate(sqls);
```
2. `int[] batchUpdate(String sql, List<Object[]> args)` 执行相同结构的 sql 语句。

```java
String sql = "insert into selection(student,course) values(?,?)";
List<Object[]> list = new ArrayList<Object[]>();
list.add(new Object[]{1005,1001});
list.add(new Object[]{1005,1003});
jdbcTemplate.batchUpdate(sql,list);
```

## 4. 查询

### I. 简单数据项

获取一个结果。
```java
String sql = "select count(*) from student";
int count = jdbcTemplate.queryForObject(sql,Integer.class);
System.out.println(count);
```

获取多个结果。
```java
String sql = "select name from student where sex=?";
List<String> names = jdbcTemplate.queryForList(sql,String.class,"女");
System.out.println(names);
```

### II. 复杂对象（封装为 Map）

获取一个结果。
```java
String sql = "select * from student where id = ?";
Map<String,Object> stu = jdbcTemplate.queryForMap(sql,1003);
System.out.println(stu);
```

获取多个结果。
```java
String sql = "select * from student";
List<Map<String,Object>> stus = jdbcTemplate.queryForList(sql);
System.out.println(stus);
```

### III. 复杂对象（封装为实体对象）

获取一个结果。
```java
String sql = "select * from student where id = ?";
Student stu = jdbcTemplate.queryForObject(sql, new StudentRowMapper(), 1004);
System.out.println(stu);
```

获取多个结果。
```java
String sql = "select * from student";
List<Student> stus = jdbcTemplate.query(sql,new StudentRowMapper());
System.out.println(stus);
```

StudentRowMapper。
```java
private class StudentRowMapper implements RowMapper<Student>{
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {
        Student stu = new Student();
        stu.setId(resultSet.getInt("id"));
        stu.setName(resultSet.getString("name"));
        stu.setSex(resultSet.getString("sex"));
        stu.setBorn(resultSet.getDate("born"));
        return stu;
    }
}
```
