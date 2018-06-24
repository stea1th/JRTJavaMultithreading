package com.javarush.task.task25.task2512;


import java.util.LinkedList;
import java.util.List;

/*
Живем своим умом
*/
public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();
        List<Throwable> list = new LinkedList<>();
        while(e.getCause()!=null){
            list.add(e);
            e = e.getCause();
        }
        list.add(e);

        for (int i = list.size()-1; i >-1 ; i--) {
            System.out.println(list.get(i));
        }
    }

    public static void main(String[] args) throws Exception{
        Thread thread = new Solution(). new MyThread();
        thread.setUncaughtExceptionHandler(new Solution());
        thread.start();
    }

    class MyThread extends Thread {


        @Override
        public void run() {
            myExceptions();
        }

        public void myExceptions() {
            throw new RuntimeException("DEF", new IllegalAccessException("GHI"));
        }
    }
}
