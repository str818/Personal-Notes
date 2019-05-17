package com.str818.principle;

public class App {

    public static boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            int i = 0;
            while(!stop){
                i++;
            }
        });
        t1.start();
        Thread.sleep(1000);
        stop = true;
    }
}
