package com.cy.produce_consume;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class UseLock {
    ReentrantLock lock = new ReentrantLock();
    Condition full = lock.newCondition();
    Condition empty = lock.newCondition();
    LinkedList<Integer> list = new LinkedList<>();
    int max = 3;

    public static void main(String[] args) {
        UseLock useLock =new UseLock();
        ExecutorService service = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 2; i++) {
            service.submit(useLock.new Productor());
        }
        for (int i = 0; i < 2; i++) {
            service.submit(useLock.new Consumer());
        }
    }
    class Productor implements Runnable {

        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    //有可能被唤醒后还是满的
                    while (list.size() == max) {
                        System.out.println("生产者" + Thread.currentThread().getName() + "  list以达到最大容量，进行wait");
                        full.await();
                        System.out.println("生产者" + Thread.currentThread().getName() + "  退出wait");
                    }
                    Random random = new Random();
                    int i = random.nextInt();
                    System.out.println("生产者" + Thread.currentThread().getName() + " 生产数据" + i);
                    list.add(i);
                    Thread.sleep(2000);
                    empty.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
        
    }
    class Consumer implements Runnable {

        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    while (list.isEmpty()) {
                        System.out.println("消费者" + Thread.currentThread().getName() + "  list为空，进行wait");
                        empty.await();
                        System.out.println("消费者" + Thread.currentThread().getName() + "  退出wait");
                    }
                    Integer element = list.remove(0);
                    System.out.println("消费者" + Thread.currentThread().getName() + "  消费数据：" + element);
                    Thread.sleep(2000);
                    full.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
        
    }
}
