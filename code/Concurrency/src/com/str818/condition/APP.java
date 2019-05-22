package com.str818.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class APP {

    public static void main(String[] args) {

        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(new ConditionWait(lock, condition)).start();
        new Thread(new ConditionSignal(lock, condition)).start();
    }

}
