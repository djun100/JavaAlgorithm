package com.cy.produce_consume;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * 实现思路：
 * 1、循环内部抢锁
 * 2、等待唤醒与同步使用同一个锁对象
 * 3、生产与消费相互等待与唤醒
 * */
public class WaitNotifyAll {
    List<Integer> storage = new ArrayList<>();

    class Produce implements Runnable {

        @Override
        public void run() {
            //要循环尝试获取锁，而不是只尝试获取一次锁，里面再循环生产
            //生产和消费共抢同一个锁，同一时间只能有一个在生产或消费，性能低下
            //xxx.notifyAll()系列方法的调用主体xxx只能是syncronized当前持有的锁，无法实现生产和消费使用不同的锁
            while (true) {
                //抢锁
                synchronized (WaitNotifyAll.class) {

                    try {
                        if (storage.size() >= 3) {
                            WaitNotifyAll.class.wait();
                        }else {
                            storage.add(new Random().nextInt());
                            System.out.println(Thread.currentThread()+"生产了一个，总数：" + storage.size());
                            Thread.sleep(1000);
                            //通知wait处继续执行
                            //当前对象为Produce实例，与synchronized持有的对象不同，会报错
//                            notifyAll();
                            WaitNotifyAll.class.notifyAll();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    class Consume implements Runnable {

        @Override
        public void run() {
            while (true) {
                synchronized (WaitNotifyAll.class) {
                    try {
                        if (storage.size() == 0) {
                            WaitNotifyAll.class.wait();
                        }else {
                            storage.remove(storage.size() - 1);
                            System.out.println(Thread.currentThread()+"消费了一个，总数：" + storage.size());
                            Thread.sleep(1000);
                            WaitNotifyAll.class.notifyAll();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        WaitNotifyAll instance = new WaitNotifyAll();
        new Thread(instance.new Produce()).start();
        new Thread(instance.new Produce()).start();
        new Thread(instance.new Consume()).start();
        new Thread(instance.new Consume()).start();
    }
}
