package com.str818.basic;

import java.util.concurrent.LinkedBlockingQueue;

public class SaveProcessor extends Thread implements IRequestProcessor{
    LinkedBlockingQueue<Request> requests = new LinkedBlockingQueue<>();

    private  IRequestProcessor nextProcessor;

    private volatile boolean isFinish = false;

    public SaveProcessor() {
    }

    public SaveProcessor(IRequestProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    public void shutdown(){
        isFinish = true;
    }

    @Override
    public void process(Request request) {
        requests.add(request);
    }

    @Override
    public void run() {
        while (!isFinish){
            try {
                Request request = requests.take();
                System.out.println("SaveProcessor:" + request);
                nextProcessor.process(request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
