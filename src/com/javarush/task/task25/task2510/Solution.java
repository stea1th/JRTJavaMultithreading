package com.javarush.task.task25.task2510;

import java.io.IOException;

/*
Поживем - увидим
*/
public class Solution extends Thread {
    @Override
    public void run() {
       throw new RuntimeException();

    }

    public Solution() {
        this.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                if(e instanceof Error){
                    System.out.println("Нельзя дальше работать");
                }else if(e instanceof Exception){
                    System.out.println("Надо обработать");
                }else if(e instanceof Throwable){
                    System.out.println("ХЗ");
                }
            }
        });

    }

    public static void main(String[] args) {
        Thread thread = new Solution();
        thread.start();

    }
}
