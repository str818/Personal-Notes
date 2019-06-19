
# 一、索引

是一种为了加速对数据表中数据行的检索从而创建的分散存储的数据结构。





## 1. 数据库的三范式是什么？

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

## 什么是事务？

事务是逻辑上的一组操作，要么都执行，要么都不执行。

#### 事务的四大特性（ACID）

1. ##### 原子性（Atomicity）

   事务是最小的执行单位，不允许分割。事务的原子性确保动作要么全部完成，要么完全不起作用。

2. ##### 一致性（Consistency）

   执行事务前后，数据保持一致，多个事务对同一个数据读取的结果是相同的。

   一致性是指数据处于一种语义上有意义且正确的状态。

   例如，A 和 B 账户中共有 500 + 500 = 1000 元，转账后，A 和 B 账户的还是共有 400 + 600 = 1000 元。也就是说，数据的状态在执行该事务后从一个状态改变到了另外一个状态。同时一致性还能保证账户余额不会变成负数等。

3. ##### 隔离性（Isolation）

   并发访问数据库时，一个用户的事务不被其他事务所干扰，各并发事务之间数据库是独立的。

4. ##### 持久性（Durability）

   一个事务被提交之后。它对数据库中数据的改变是持久的，即使数据库发生故障也不应该对其有任何影响。使用重做日志来保证持久性。

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

- 并发：MyISAM 只支持表级锁；而 InnoDB 支持行级锁和表级锁，默认为行级锁。
- 事务：MyISAM 强调的是性能，每次查询具有原子性，执行速度比 InnoDB 更快，但不提供事务支持；InnoDB 提供事务支持、外键等高级数据库功能。
- 外键：MyISAM 不支持外键；InnoDB 支持外键。
- 备份：InnoDB 支持在线热备份。
- 崩溃恢复：MyISAM 崩溃后发生损坏的概率比 InnoDB 高很多，而且恢复的速度也更慢。
- 其它特性：MyISAM 支持压缩表和空间数据索引。

## 参考

[事务隔离级别浅析](<http://geyifan.cn/2016/07/17/talk-about-transaction/>)

[CyC2018 - 数据库系统原理]([https://github.com/CyC2018/CS-Notes/blob/master/notes/%E6%95%B0%E6%8D%AE%E5%BA%93%E7%B3%BB%E7%BB%9F%E5%8E%9F%E7%90%86.md#acid](https://github.com/CyC2018/CS-Notes/blob/master/notes/数据库系统原理.md#acid))

[彻底理解数据库事务](<https://www.hollischuang.com/archives/898>)

