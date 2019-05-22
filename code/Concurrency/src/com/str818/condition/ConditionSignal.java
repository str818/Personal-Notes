package com.str818.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ConditionSignal implements Runnable{

    private Lock lock;
    private Condition condition;

    public ConditionSignal(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {


        try {
            lock.lock();
            System.out.println("begin -ConditionSignal");
            condition.signal();
            System.out.println("end -ConditionSignal");
        } finally {
            lock.unlock();
        }
    }
}
