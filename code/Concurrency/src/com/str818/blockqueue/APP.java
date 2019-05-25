package com.str818.blockqueue;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class APP {

    public static void main(String[] args) {
        BlockingQueue<Integer> bq = new BlockingQueue<>(3);
//        new Thread(()->{
//            try {
//                bq.take();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }, "t1").start();
//
//        new Thread(()->{
//            try {
//                TimeUnit.SECONDS.sleep(1);
//                bq.take();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }, "t2").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
                bq.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t3").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
                bq.put(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t4").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
                bq.put(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t5").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
                bq.put(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t6").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(4);
                bq.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t7").start();

    }

}
