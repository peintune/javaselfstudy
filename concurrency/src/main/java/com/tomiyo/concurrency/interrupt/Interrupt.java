package com.tomiyo.concurrency.interrupt;

import javax.swing.plaf.basic.BasicScrollPaneUI;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @description: $任何阻塞操作（sleep, blockqueue, rpc, ）在遇到interrupt后都会抛出 InterruptedException
 * @author: He Kun
 * @create: 2019-03-07 10:51
 **/
public class Interrupt {

    public static void main(String[] args){
        HashMap<Integer,String> map = new HashMap<Integer, String>();
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(1000000);
        int k =0;
        while(k <10000){
            map.put(k, "s");
            k++;
        }
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int i = 0;
                try{
                    while (i<1000){
                      // Thread.sleep(500);  //interrupted exception
                        for(int j=0;j<10000;j++){
                            String value = map.get(j);
                           // queue.put(value); //interrupted exception
                        }
                        System.out.println(i++);
                    }
                }catch (Exception e){
                        e.printStackTrace();
                }
            }
        };


        Thread t = new Thread(runnable);
        System.out.println("start !");
        t.start();

//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            System.out.println("error");
//            e.printStackTrace();
//        }

        t.interrupt();
    }
}
