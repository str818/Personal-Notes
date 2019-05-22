package com.str818.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ConditionWait implements Runnable{

    private Lock lock;
    private Condition condition;

    public ConditionWait(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {


        try {
            lock.lock();
            System.out.println("begin -ConditionWait");
            condition.await();
            System.out.println("end -ConditionWait");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
