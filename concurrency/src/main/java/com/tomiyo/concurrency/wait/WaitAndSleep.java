package com.tomiyo.concurrency.wait;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @description: sleep 是线程的，wait是所有Object 类型对象的， sleep不会让出cpu, wait会直接让出cpu
 * @author: He Kun
 * @create: 2019-03-07 10:35
 **/
public class WaitAndSleep {

    public static void main(String[] args){
        Object lock = new Object();
        try {
            System.out.println("test2");
          //  lock.wait(1000);  IllegalMonitorStateException
            System.out.println("test");
        } catch (Exception e){
            e.printStackTrace();
        }

        new Thread(()->{
            System.out.println("Thread A is waiting to get lock");
                synchronized (lock){
                    System.out.println("Thead A get the lock");
                    try {
                        //object.wait 会让出时间片
                        lock.wait(1000);
                        System.out.println("Thread A done");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }).start();

        //为了让线程A 和线程B是按照 A->B 的顺序执行的
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            System.out.println("Thread B is waiting to get lock");
            synchronized (lock){
                System.out.println("Thead B get the lock");
                try {
                    //lock.wait(1000);
                    //lock.notifyAll();
                    Thread.sleep(3000);
                    System.out.println("Thread B done");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}

class MyThread implements  Runnable{
    public void run() {
        int i = 0;

        while (i++ < 10){
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
