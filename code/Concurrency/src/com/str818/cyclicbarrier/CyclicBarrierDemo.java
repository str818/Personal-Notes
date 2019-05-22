package com.str818.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo extends Thread{

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new CyclicBarrierDemo());
        new Thread(new DataImportThread(cyclicBarrier, "file1")).start();
        new Thread(new DataImportThread(cyclicBarrier, "file2")).start();
        new Thread(new DataImportThread(cyclicBarrier, "file3")).start();
    }

    @Override
    public void run() {
        System.out.println("开始进行数据分析");
    }
}
