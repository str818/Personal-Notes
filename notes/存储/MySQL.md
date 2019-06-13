
# 数据库三大范式

- **第一范式：列不可拆分**

```sql
-- address 可以继续拆分为 国家、省份、地区  不满足第一范式
create table student(
	id int primary key,
    name varchar(20),
    address varchar(30)
)
```


- **第二范式：满足第一范式前提下，除主键外的每一列都必须完全依赖于主键**

```sql
-- customer_name 只与 customer_id 有关，也就是除主键以外的其他列，只依赖于主键的部分字段。
create table myorder(
	product_id int,
    customer_id int,
    product_name varchar(20),
    customer_name varchar(20),
    primary key(product_id, customer_id)
);
```

可以拆分为：

```sql
-- 订单表
create table myorder(
	order_id int primary key,
    product_id int,
    customer_id int
);
-- 产品表
create table product(
	id int primary key,
    name varchar(20)
);
-- 消费者表
create table customer(
	id int primary key,
    name varchar(20)
);
```

- **第三范式：满足第二范式的前提下，除开主键列的其他列之间不能有传递依赖关系**

```sql
-- customer_phone 与非主键的 customer_id 存在依赖关系，不满足第三范式
create table myorder(
	order_id int primary key,
    product_id int,
    customer_id int,
    customer_phone varchar(15)
);
```

