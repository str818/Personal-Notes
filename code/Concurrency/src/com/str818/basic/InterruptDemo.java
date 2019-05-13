package com.str818.basic;

import java.util.concurrent.TimeUnit;

public class InterruptDemo {

    private static int i;
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            // 默认为 false，通过 thread.interrupt() 后变成 true
            while(!Thread.currentThread().isInterrupted()){
                i++;
            }
            System.out.println("i:" + i);
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
    }
}
