package com.cy.produce_consume;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UseLock {
    List<Integer> list = new ArrayList<>();
    int max = 3;
    Lock lock =new ReentrantLock();
    Condition notFullCondition = lock.newCondition();
    Condition notEmptyCondition = lock.newCondition();

    class Produce implements Runnable{

        @Override
        public void run() {
            while (true) {
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread()+"通过锁进入生产");
                    if (list.size()<max){
                        list.add(new Random().nextInt());
                        System.out.println(Thread.currentThread()+"总量："+list.size());
                        Thread.sleep(2000);
                    }else {
                        System.out.println(Thread.currentThread()+"生产已满，等待");
                        notFullCondition.await();
                    }
                    System.out.println(Thread.currentThread()+"不空，通知消费");
                    notEmptyCondition.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread()+"通过锁退出生产");
                    lock.unlock();
                }
            }
        }
    }

    class Consume implements Runnable{

        @Override
        public void run() {
            while (true) {
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread()+"通过锁进入消费");
                    if (list.size()>0){
                        list.remove(list.size()-1);
                        System.out.println(Thread.currentThread()+"总量："+list.size());
                        Thread.sleep(2000);
                    }else {
                        System.out.println(Thread.currentThread()+"已消费空，等待");
                        notEmptyCondition.await();
                    }
                    System.out.println(Thread.currentThread()+"不满，通知生产");
                    notFullCondition.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread()+"通过锁退出消费");
                    lock.unlock();
                }
            }

        }
    }

    public static void main(String[] args) {
        UseLock useLock = new UseLock();
        new Thread(useLock.new Produce()).start();
        new Thread(useLock.new Produce()).start();
        new Thread(useLock.new Consume()).start();
        new Thread(useLock.new Consume()).start();
    }
}
