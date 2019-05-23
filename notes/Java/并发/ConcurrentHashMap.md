# put 操作

```java
public V put(K key, V value) {
    return putVal(key, value, false);
}
```

```java
final V putVal(K key, V value, boolean onlyIfAbsent) {
    if (key == null || value == null) throw new NullPointerException();
    // 计算 hash 值
    int hash = spread(key.hashCode());
    // 记录链表的长度
    int binCount = 0;
    // 自旋操作
    for (Node<K,V>[] tab = table;;) {
        Node<K,V> f; int n, i, fh;
        // 如果数组为空，对数据进行初始化
        if (tab == null || (n = tab.length) == 0)
            tab = initTable();
        // 通过 hash 值对应的数组下标得到第一个节点; 以 volatile 读的方式来读取 table 数组中的元素，保证每次拿到的数据都是最新的
        else if ((f = tabAt(tab, i = (n - 1) & hash)) == null) {
            // 如果该下标返回的节点为空，则直接通过 cas 将新的值封装成 node 插入即可；如果 cas 失败，说明存在竞争，则进入下一次循环
            if (casTabAt(tab, i, null, new Node<K,V>(hash, key, value, null)))
                break;
        }
        else if ((fh = f.hash) == MOVED)
            tab = helpTransfer(tab, f);
        else {
            V oldVal = null;
            synchronized (f) {
                if (tabAt(tab, i) == f) {
                    if (fh >= 0) {
                        binCount = 1;
                        for (Node<K,V> e = f;; ++binCount) {
                            K ek;
                            if (e.hash == hash &&
                                ((ek = e.key) == key ||
                                    (ek != null && key.equals(ek)))) {
                                oldVal = e.val;
                                if (!onlyIfAbsent)
                                    e.val = value;
                                break;
                            }
                            Node<K,V> pred = e;
                            if ((e = e.next) == null) {
                                pred.next = new Node<K,V>(hash, key,
                                                            value, null);
                                break;
                            }
                        }
                    }
                    else if (f instanceof TreeBin) {
                        Node<K,V> p;
                        binCount = 2;
                        if ((p = ((TreeBin<K,V>)f).putTreeVal(hash, key,
                                                        value)) != null) {
                            oldVal = p.val;
                            if (!onlyIfAbsent)
                                p.val = value;
                        }
                    }
                }
            }
            if (binCount != 0) {
                if (binCount >= TREEIFY_THRESHOLD)
                    treeifyBin(tab, i);
                if (oldVal != null)
                    return oldVal;
                break;
            }
        }
    }
    addCount(1L, binCount);
    return null;
}
```

## 初始化数组 initTable

sizeCtl 是在 Node 数组初始化或扩容时的一个控制标识。

- -1：正在初始化；
- -N：有 N-1 有两个线程正在进行扩容操作；
- 0：Node 数组还没有被初始化，正数代表初始化或者下一次扩容的大小。

```java
private final Node<K,V>[] initTable() {
    Node<K,V>[] tab; 
    int sc;
    while ((tab = table) == null || tab.length == 0) {
        // 被其他线程抢占了初始化的操作,则直接让出自己的 CPU 时间片
        if ((sc = sizeCtl) < 0)
            Thread.yield();
        // 通过 cas 操作，将 sizeCtl 替换为-1，标识当前线程抢占到了初始化资格
        else if (U.compareAndSwapInt(this, SIZECTL, sc, -1)) {
            try {
                if ((tab = table) == null || tab.length == 0) {
                    int n = (sc > 0) ? sc : DEFAULT_CAPACITY; //默认初始容量为 16
                    @SuppressWarnings("unchecked")
                    // 初始化数组，长度为 16，或者初始化在构造 ConcurrentHashMap 的时候传入的长度
                    Node<K,V>[] nt = (Node<K,V>[])new Node<?,?>[n];
                    table = tab = nt; // 将这个数组赋值给 table
                    // 计算下次扩容的大小，实际就是当前容量的 0.75 倍，这里使用了右移来计算
                    sc = n - (n >>> 2);
                }
            } finally {
                // 设置 sizeCtl 为 sc, 如果默认是 16 的话，那么这个时候 sc = 16 * 0.75 = 12
                sizeCtl = sc;
            }
            break;
        }
    }
    return tab;
}
```

## tabAt

实际上该方法类似于 tab[i]，但是要保证元素的可见性，直接通过 Unsafe 类对 table 进行操作。

```java
static final <K,V> Node<K,V> tabAt(Node<K,V>[] tab, int i) {
    return (Node<K,V>)U.getObjectVolatile(tab, ((long)i << ASHIFT) + ABASE);
}
```

## addCount

参数 x 表示此次需要增加元素的个数；参数 check 表示是否需要进行扩容检查，大于等于 0 都需要进行检查。
```java
private final void addCount(long x, int check) {
    CounterCell[] as; long b, s;

    // 判断 counterCells 是否为空：
    // 1. 如果为空，就通过 cas 操作尝试修改 baseCount 变量，对这个变量进行原子累加操作(做这个操作的意义是：如果在没有竞争的情况下，仍然采用 baseCount 来记录元素数)
    // 2. 如果 cas 失败说明存在竞争，这个时候不能再采用 baseCount 来累加，而是通过 CounterCell 来记录
    if ((as = counterCells) != null ||
        !U.compareAndSwapLong(this, BASECOUNT, b = baseCount, s = b + x)) {
        CounterCell a; long v; int m;
        boolean uncontended = true; // 是否冲突标识，默认为没有冲突
        // 这里有几个判断
        // 1. 计数表为空则直接调用 fullAddCount
        // 2. 从计数表中随机取出一个数组的位置为空，直接调用 fullAddCount
        // 3. 通过 CAS 修改 CounterCell 随机位置的值，如果修改失败说明出现并发情况（这里又用到了一种巧妙的方法），调用 fullAndCount
        // Random 在线程并发的时候会有性能问题以及可能会产生相同的随机数,ThreadLocalRandom.getProbe 可以解决这个问题，并且性能要比 Random 高
        if (as == null || (m = as.length - 1) < 0 ||
            (a = as[ThreadLocalRandom.getProbe() & m]) == null ||
            !(uncontended = U.compareAndSwapLong(a, CELLVALUE, v = a.value, v + x))) {
            fullAddCount(x, uncontended);
            return;
        }
        if (check <= 1) // 链表长度小于等于 1，不需要考虑扩容
            return;
        s = sumCount(); // 统计 ConcurrentHashMap 元素个数
    }
    // 如果 binCount>=0，标识需要检查扩容
    if (check >= 0) {
        Node<K,V>[] tab, nt; int n, sc;
        // s 标识集合大小，如果集合大小大于或等于扩容阈值（默认值的 0.75）并且 table 不为空并且 table 的长度小于最大容量
        while (s >= (long)(sc = sizeCtl) && (tab = table) != null &&
                (n = tab.length) < MAXIMUM_CAPACITY) {
            int rs = resizeStamp(n); // 这里是生成一个唯一的扩容戳
            if (sc < 0) {
                if ((sc >>> RESIZE_STAMP_SHIFT) != rs || sc == rs + 1 ||
                    sc == rs + MAX_RESIZERS || (nt = nextTable) == null ||
                    transferIndex <= 0)
                    break;
                if (U.compareAndSwapInt(this, SIZECTL, sc, sc + 1))
                    transfer(tab, nt);
            }
            else if (U.compareAndSwapInt(this, SIZECTL, sc,
                                            (rs << RESIZE_STAMP_SHIFT) + 2))
                transfer(tab, null);
            s = sumCount();
        }
    }
}
```

### CounterCells

ConcurrentHashMap 采用 CounterCell 数组来记录元素个数。如果用一个成员变量来统计元素个数，为了保证并发情况下的正确运行，需要通过加锁或自旋的方式实现，在竞争激烈的情况下，size 的设置上会出现较大的冲突从而影响性能。

CounterCell 数组中的每个元素，都存储一个数字，实际调用 size 方法是通过遍历 CounterCell 数组累加得到的。

```java
// 标识当前 cell 数组是否在初始化或扩容中的 CAS 标志位
private transient volatile int cellsBusy;
// counterCells 数组，总数值的分值分别存在每个 cell 中
private transient volatile CounterCell[] counterCells;

@sun.misc.Contended static final class CounterCell {
    volatile long value;
    CounterCell(long x) { value = x; }
}

final long sumCount() {
    CounterCell[] as = counterCells; CounterCell a;
    long sum = baseCount;
    if (as != null) {
        for (int i = 0; i < as.length; ++i) {
            if ((a = as[i]) != null)
                sum += a.value;
        }
    }
    return sum;
}
```

### fullAddCount

初始化 CounterCell，记录元素个数。初始化长度为 2 的数组，然后随机得到指定的一个数组下标，将需要新增的值加入到对应下标位置处。

```java
private final void fullAddCount(long x, boolean wasUncontended) {
    int h;
    // 获取当前线程的 probe 的值，如果值为 0，则初始化当前线程的 probe 的值,probe 就是随机数
    if ((h = ThreadLocalRandom.getProbe()) == 0) {
        ThreadLocalRandom.localInit();      // force initialization
        h = ThreadLocalRandom.getProbe();
        wasUncontended = true; // 由于重新生成了 probe，未冲突标志位设置为 true
    }
    boolean collide = false;                // True if last slot nonempty
    for (;;) { // 自旋
        CounterCell[] as; CounterCell a; int n; long v;
        // counterCells 已经被初始化过了
        if ((as = counterCells) != null && (n = as.length) > 0) {
            // 通过该值与当前线程 probe 求与，获得 cells 的下标元素，和 hash 表获取索引是一样的
            if ((a = as[(n - 1) & h]) == null) {
                // cellsBusy=0 表示 counterCells 不在初始化或者扩容状态下
                if (cellsBusy == 0) {            // Try to attach new Cell
                    CounterCell r = new CounterCell(x); //构造一个 CounterCell 的值，传入元素个数
                    if (cellsBusy == 0 &&   // 通过 cas 设置 cellsBusy 标识，防止其他线程来对 counterCells 并发处理
                        U.compareAndSwapInt(this, CELLSBUSY, 0, 1)) {
                        boolean created = false;
                        try {               // Recheck under lock
                            CounterCell[] rs; int m, j;
                            // 将初始化的 r 对象的元素个数放在对应下标的位置
                            if ((rs = counterCells) != null &&
                                (m = rs.length) > 0 &&
                                rs[j = (m - 1) & h] == null) {
                                rs[j] = r;
                                created = true;
                            }
                        } finally { // 恢复标志位
                            cellsBusy = 0;
                        }
                        if (created) // 创建成功，退出循环
                            break;
                        continue; // 说明指定 cells 下标位置的数据不为空，则进行下一次循环
                    }
                }
                collide = false;
            }
            // 说明在 addCount 方法中 cas 失败了，并且获取 probe 的值不为空
            else if (!wasUncontended)       // CAS already known to fail
                wasUncontended = true; // 设置为未冲突标识，进入下一次自旋
            // 由于指定下标位置的 cell 值不为空，则直接通过 cas 进行原子累加，如果成功，则直接退出
            else if (U.compareAndSwapLong(a, CELLVALUE, v = a.value, v + x))
                break;
            //如果已经有其他线程建立了新的 counterCells 或者 CounterCells 大于 CPU 核心数（很巧妙，线程的并发数不会超过 cpu 核心数）
            else if (counterCells != as || n >= NCPU)
                collide = false; // 设置当前线程的循环失败不进行扩容
            else if (!collide) // 设置当前线程的循环失败不进行扩容
                collide = true;
            // 进入这个步骤，说明 CounterCell 数组容量不够，线程竞争较大，所以先设置一个标识表示为正在扩容
            else if (cellsBusy == 0 &&
                        U.compareAndSwapInt(this, CELLSBUSY, 0, 1)) {
                try {
                    if (counterCells == as) {// Expand table unless stale
                        // 扩容一倍 2 变成 4，这个扩容比较简单
                        CounterCell[] rs = new CounterCell[n << 1];
                        for (int i = 0; i < n; ++i)
                            rs[i] = as[i];
                        counterCells = rs;
                    }
                } finally {
                    cellsBusy = 0; // 恢复标识
                }
                collide = false;
                continue; // 继续下一次自旋
            }
            h = ThreadLocalRandom.advanceProbe(h); // 更新随机数的值
        }
        // cellsBusy=0 表示没有在做初始化，通过 cas 更新 cellsbusy 的值标注当前线程正在做初始化操作
        else if (cellsBusy == 0 && counterCells == as &&
                    U.compareAndSwapInt(this, CELLSBUSY, 0, 1)) {
            boolean init = false;
            try {                           // Initialize table
                if (counterCells == as) {
                    CounterCell[] rs = new CounterCell[2]; // 初始化容量为 2
                    rs[h & 1] = new CounterCell(x); // 将 x 也就是元素的个数放在指定的数组下标位置
                    counterCells = rs; // 将 x 也就是元素的个数放在指定的数组下标位置
                    init = true; // 设置初始化完成标识
                }
            } finally {
                cellsBusy = 0; // 恢复标识
            }
            if (init)
                break;
        }
        // 竞争激烈，其它线程占据 cell 数组，直接累加在 base 变量中
        else if (U.compareAndSwapLong(this, BASECOUNT, v = baseCount, v + x))
            break;                          // Fall back on using base
    }
}
```