package com.str818.basic;

import java.sql.SQLOutput;
import java.util.concurrent.TimeUnit;

public class InterruptDemo2 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            // 默认为 false，通过 thread.interrupt() 后变成 true
            while(true){
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("before：" + Thread.currentThread().isInterrupted());
                    Thread.interrupted(); // 对线程进行复位，由 true 变成 false
                    System.out.println("after：" + Thread.currentThread().isInterrupted());
                }
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
    }
}
