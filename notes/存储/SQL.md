

# 约束

## 主键约束

它能够唯一确定一张表中的一条记录，也就是我们通过某个字段添加约束，就可以使得该字段不重复且不为空。

```sql
create table user(
    id int primary key,
    name varchar(20)
);
```

```sql
-- 联合主键
-- 只要联合的主键值加起来不重复就可以
create table user(
    id int,
    name varchar(20),
    password carchar(20),
    primary key(id, name)
);
```

```sql
-- 添加主键
alter table user add primary key(id);
-- 删除主键
alter table user drop primary key;
-- 修改主键
alter table user modify id int primary key;
```

## 自增约束

```sql
create table user(
    id int primary key auto_increment,
    name varchar(20)
);
```

## 唯一约束

约束修饰的字段的值不可以重复。

```sql
create table user(
	id int, 
    name varchar(20),
    unique(name)
);

-- 添加唯一约束
alter table user add unique(name);
alter table user modify name varchar(20) unique;
-- 删除唯一约束
alter table user drop index name;
```

## 非空约束

修饰的字段不能为空 NULL。

```sql
create table user(
	id int,
    name varchar(20) not null
);
```

## 默认约束

插入字段值的时候，如果没有传值，就会使用默认值。

```sql
create table user(
	id int,
    name varchar(20),
    age int default 10
);
```

## 外键约束

主表中没有的数据值，在副表中，是不可以使用的；

主表中的记录被副表引用时，是不可以被删除的。

```sql
-- 班级
create table classes(
	id int primary key,
    name varchar(20)
); 
-- 学生表
create table students(
	id int primary key,
    name varchar(20),
    class_id int,
    foregin key(class_id) references classes(id)
);
```

