package com.cy.produce_consume;

import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class UseBlockingQueue {
    BlockingDeque<Integer> blockingDeque = new LinkedBlockingDeque<>(3);

    class Produce implements Runnable {

        @Override
        public void run() {
            while (true) {

                try {
                    blockingDeque.put(new Random().nextInt());
                    Thread.sleep(500);
                    //不要输出总数，因为非原子操作，不同步
                    System.out.println(Thread.currentThread() + "生产了一个");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    class Consume implements Runnable {

        @Override
        public void run() {
            while (true) {
                if (blockingDeque.size() > 0) {
                    try {
                        blockingDeque.take();
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread() + "消费了一个");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        UseBlockingQueue useBlockingQueue = new UseBlockingQueue();
        new Thread(useBlockingQueue.new Produce()).start();
        new Thread(useBlockingQueue.new Produce()).start();
        new Thread(useBlockingQueue.new Consume()).start();
        new Thread(useBlockingQueue.new Consume()).start();
    }
}
