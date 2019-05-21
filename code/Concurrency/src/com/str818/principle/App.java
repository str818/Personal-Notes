package com.str818.principle;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class App {

    public static boolean stop = false;

    public static void main(String[] args) throws InterruptedException {

        ReadWriteLock lock = new ReentrantReadWriteLock();

        lock.readLock().lock();
    }
}
