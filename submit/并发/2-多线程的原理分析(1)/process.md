1. 请分析以下程序的执行结果,并详细说明原因

```java
public class SynchronizedDemo implements Runnable{
    int x = 100;

    public synchronized void m1() {
        x = 1000;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("x=" + x);
    }

    public synchronized void m2() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        x = 2000;
    }
    public static void main(String[] args) throws InterruptedException {
        SynchronizedDemo sd = new SynchronizedDemo();
        new Thread(()->sd.m1()).start();
        new Thread(()->sd.m2()).start();
        sd.m2();
        System.out.println("Main x=" + sd.x);
    }
    @Override
    public void run() {
        m1();
    }
}
```

可能会出现：
(1) x = 1000，Main x = 2000
(2) x = 1000, Main x = 1000
(3) Main x = 1000, x = 1000


synchronized 修饰的是普通方法，锁是当前的实例对象。

三个线程共同竞争对象锁，同时 Main 线程的后两句代码可能会重排序，情况(1)调试出现过。

2. 下面这个程序的最终结果是多少？为什么？

```java
public class SynchronizedDemo  {
   static Integer count=0;
   public static void incr(){
       synchronized (count) {
           try {
               Thread.sleep(1);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           count++;
       }
   }
    public static void main(String[] args) throws IOException, InterruptedException {
        for(int i=0;i<1000;i++){
            new Thread(()->SynchronizedDemo.incr()).start();
        }
        Thread.sleep(5000);
        System.out.println("result:"+count);
    }
}
```

小于 1000 的一个数。

锁对象是 Integer 类型的 count，而 count 是不断进行自增操作的，每一次自增操作后 count 对象都和上一次不同，即便在范围 -127 ~ 128 情况下从常量池中获取对象，也是不同的对象，所以锁没有效果。