package com.str818.blockqueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue<T> {

    private Lock lock;
    private Condition fullConditon, emptyConditon;
    private int maxLength;
    private Queue<T> queue;

    public BlockingQueue(int maxLength) {
        this.lock = new ReentrantLock();
        this.fullConditon = lock.newCondition();
        this.emptyConditon = lock.newCondition();
        this.maxLength = maxLength;
        this.queue = new LinkedList<>();
    }

    public T take() throws InterruptedException {

        try {
            lock.lock();
            if (queue.size() == 0) {
                System.out.println("线程" + Thread.currentThread().getName() + "等待 get 中。。。");
                emptyConditon.await();
            }
            T t = queue.poll();
            System.out.println("线程" + Thread.currentThread().getName() + " get 到 " + t);
            fullConditon.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    public void put(T t) throws InterruptedException {
        try {
            lock.lock();
            if (queue.size() == maxLength) {
                System.out.println("线程" + Thread.currentThread().getName() + "等待 put 中。。。");
                fullConditon.await();
            }
            queue.offer(t);
            System.out.println("线程" + Thread.currentThread().getName() + " put " + t + " 完毕");
            emptyConditon.signal();
        } finally {
            lock.unlock();
        }
    }
}
