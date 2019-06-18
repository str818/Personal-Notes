
# 一、索引

是一种为了加速对数据表中数据行的检索从而创建的分散存储的数据结构。





# 数据库的三范式是什么？

- **第一范式：列不可拆分**

```sql
-- address 可以继续拆分为 国家、省份、地区  不满足第一范式
create table student(
	id int primary key,
    name varchar(20),
    address varchar(30)
)
```

- **第二范式：满足第一范式前提下，除主键外的每一列都必须完全依赖于主键，也就是每个表只描述一件事**

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

# 如何设计一个关系型数据库

<div align="center">  <img src="img/design.png" width="70%"/> </div><br>

# 为什么使用索引

索引是帮助数据库高效获取数据的数据结构。

MySQL 数据库存储数组最终是以文件的形式存储到硬盘的，一般来说，在程序中使用的时候肯定要把磁盘文件中的数据读到内存中，数据库动辄千万级的数据，读取数据花费的时间是巨大的，这时就可以通过创建索引的方式提升数据读取速率。

# B - Tree

m 阶 B-Tree 满足以下条件：

1. 每个节点最多拥有 m 个子树
2. 根节点至少有 2 个子树
3. 分支节点至少拥有 m/2 颗子树（除根节点和叶子节点外都是分支节点）
4. 所有叶子节点都在同一层、每个节点最多可以有 m-1 个 key，并且以升序排列

# B+ - Tree

1. 非叶子节点只存储键值信息。
2. 所有叶子节点之间都有一个链指针。
3. 数据记录都存放在叶子节点中。

# 数据库存储引擎

## 常用的 MySQL 存储引擎及使用场景

## MyISAM

1. 非事务型存储引擎
2. 以堆表方式存储
3. 使用表级锁
4. 支持 Btree 索引、空间索引、全文索引

### 使用场景

- 读操作远远大于写操作的场景
- 不需要使用事物的场景

## InnoDB

1. 事务型存储引擎支持 ACID
2. 数据按主键聚集存储
3. 支持行级锁及 MVCC
4. 支持 Btree 和自适应 Hash 索引
5. 支持全文和空间索引

### 使用场景

- 大多数 OLTP 场景

## InnoDB

是 MySQL 默认的事务型存储引擎，只有在需要它不支持的特性时，才考虑使用其它存储引擎。

实现了四个标准的隔离级别，默认级别是可重复读（REPEATABLE READ）。在可重复读隔离级别下，通过多版本并发控制（MVCC）+ 间隙锁（Next-Key Locking）防止幻影读。

主索引是聚簇索引，在索引中保存了数据，从而避免直接读取磁盘，因此对查询性能有很大的提升。

内部做了很多优化，包括从磁盘读取数据时采用的可预测性读、能够加快读操作并且自动创建的自适应哈希索引、能够加速插入操作的插入缓冲区等。

支持真正的在线热备份。其它存储引擎不支持在线热备份，要获取一致性视图需要停止对所有表的写入，而在读写混合场景中，停止写入可能也意味着停止读取。

## MyISAM

设计简单，数据以紧密格式存储。对于只读数据，或者表比较小、可以容忍修复操作，则依然可以使用它。

提供了大量的特性，包括压缩表、空间数据索引等。

不支持事务。

不支持行级锁，只能对整张表加锁，读取时会对需要读到的所有表加共享锁，写入时则对表加排它锁。但在表有读取操作的同时，也可以往表中插入新的记录，这被称为并发插入（CONCURRENT INSERT）。

可以手工或者自动执行检查和修复操作，但是和事务恢复以及崩溃恢复不同，可能导致一些数据丢失，而且修复操作是非常慢的。

如果指定了 DELAY_KEY_WRITE 选项，在每次修改执行完成时，不会立即将修改的索引数据写入磁盘，而是会写到内存中的键缓冲区，只有在清理键缓冲区或者关闭表的时候才会将对应的索引块写入磁盘。这种方式可以极大的提升写入性能，但是在数据库或者主机崩溃时会造成索引损坏，需要执行修复操作。

## 比较

- 事务：InnoDB 是事务型的，可以使用 Commit 和 Rollback 语句。
- 并发：MyISAM 只支持表级锁，而 InnoDB 还支持行级锁。
- 外键：InnoDB 支持外键。
- 备份：InnoDB 支持在线热备份。
- 崩溃恢复：MyISAM 崩溃后发生损坏的概率比 InnoDB 高很多，而且恢复的速度也更慢。
- 其它特性：MyISAM 支持压缩表和空间数据索引。
