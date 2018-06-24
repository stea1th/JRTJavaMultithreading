package com.javarush.task.task26.task2611;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class Producer implements Runnable {

    private ConcurrentHashMap<String, String> map;
    private int i = 1;

    public Producer(ConcurrentHashMap<String, String> map) {
        this.map = map;
    }

    @Override
    public void run() {
        while(true){
            try{
                map.put(String.valueOf(i), "Some text for "+i);
                i++;
                TimeUnit.MILLISECONDS.sleep(500);
            }catch (InterruptedException e){
                System.out.printf("[%s] thread was terminated", Thread.currentThread().getName().substring(7));
            }
        }

    }
}
