package com.javarush.task.task25.task2505;

/* 
Без дураков
*/
public class Solution {

    public static void main(String[] args) {
        MyThread myThread = new Solution().new MyThread("super secret key");
        myThread.start();

    }

    public class MyThread extends Thread {
        private String secretKey;
        public Thread thread = new Thread();

        public MyThread(String secretKey) {
            thread.start();
            this.secretKey = secretKey;
            setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
            setDaemon(false);
        }

        private class MyUncaughtExceptionHandler implements UncaughtExceptionHandler {
            public MyUncaughtExceptionHandler() {
            }

            @Override
            public void uncaughtException(Thread t, Throwable e) {
                try{
                    Thread.sleep(500);
                }catch (InterruptedException x){
                    x.printStackTrace();
                }
                System.out.println(String.format("%s, %s, %s", secretKey, t.getName(), e.getMessage()));
            }
        }

        @Override
        public void run() {
            throw new NullPointerException("it's an example");
        }
    }

}

