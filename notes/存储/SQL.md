

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

# 查询

```sql
-- 学生表
create table student(
	sno varchar(20) primary key,
    sname varchar(20) not null,
    ssex varchar(10) not null,
    sbirthday datetime,
    class varchar(20)
);
-- 教师表
create table teacher(
	tno varchar(20) primary key,
    tname varchar(20) not null,
    tsex varchar(10) not null,
    tbirthday datetime,
    prof varchar(20) not null,
    depart varchar(20) not null
);
-- 课程表
create table course(
	cno varchar(20) primary key,
	cname varchar(20) not null,
	tno varchar(20) not null,
	foreign key(tno) references teacher(tno)
);
-- 成绩表
create table score(
	sno varchar(20) not null,
    cno varchar(20) not null,
    degree decimal,
    foreign key(sno) references student(sno),
    foreign key(cno) references course(cno),
    primary key(sno, cno)
);

insert into sutdent values('101','str818_1','男','1997-01-08','')
```

```sql
-- 1. 查询 student 表的所有记录。
select * from student;

-- 2. 查询 student 表中的所有记录的 sname、ssex 和 class 列。
select sname, ssex, class from student;

-- 3. 查询教师所有的单位即不重复的 depart 列。
-- distinct 排除重复
select distinct depart from teacher;

-- 4. 查询 score 表中成绩在 60 到 80 之间的所有记录。
-- 查询区间 between .. and..
select * from score where degree between 60 and 80;
-- 运算符比较
select * from score where degree > 60 and degree < 80;

-- 5. 查询 score 表中成绩为 85、86 或 88 的记录。
-- 表示或者关系的查询 in
select * from score where degree in(85, 86, 88);

-- 6. 查询 student 表中 '95031' 班或者性别为 '女' 的同学记录。
-- or 表示或者
select * from student where class='95031' or ssex='女';

-- 7. 以 class 降序查询 student 表的所有记录。
-- 降序 desc
select * from student order by class desc;
-- 升序（默认 asc）
select * from student order by class;

-- 8. 以 cno 升序、degree 降序查询 score 表的所有记录。
select * from score order by cno asc, degree desc;

-- 9. 查询 '95031' 班的学生人数。
-- 统计 count
select count(*) from student where class='95031';

-- 10. 查询 score 表中的最高分的学生学号和课程号。
select sno, cno from score where degree=(select max(degree) from score);

-- 11. 查询每门课的平均成绩。
-- group by 分组
select cno,avg(degree) from score group by cno;

-- 12. 查询 score 表中至少有 2 名学生选修的并以 3 开头的课程的平均分数。
select cno,avg(degree),count(*) from score group by cno
having count(cno) >= 2 and cno like '3%';

-- 13. 查询所有学生的 sname、cno 和 degree 列。
-- 多表查询
select sname, cno, degree from student,score where student.sno=score.sno;

-- 14. 查询所有学生的 sname、cname 和 degree 列。
-- 三表联查
select sname,cname,degree from student,course,socre
where student.sno=score.sno
and course.sno=score.cno;

-- 15. 查询 '95031' 班学生每门课的平均分。

```

