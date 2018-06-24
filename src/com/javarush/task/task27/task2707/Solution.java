package com.javarush.task.task27.task2707;

import java.util.concurrent.TimeUnit;

/*
Определяем порядок захвата монитора
*/
public class Solution {
    public void someMethodWithSynchronizedBlocks(Object obj1, Object obj2) {
        synchronized (obj1) {
            synchronized (obj2) {
                System.out.println(obj1 + " " + obj2);
            }
        }
    }

    public static boolean isNormalLockOrder(Solution solution, final Object o1, final Object o2) throws Exception {
        solution.someMethodWithSynchronizedBlocks(o1, o2);
        Thread thread = new Thread(){
            @Override
            public void run() {
                synchronized (o1){
                    synchronized (o2){

                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {

                    }

                }
            }
        };

        thread.start();

        if(Thread.State.TIMED_WAITING.equals(thread.getState())){
            return true;
        }else
            return false;
    }

    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        final Object o1 = new Object();
        final Object o2 = new Object();

        System.out.println(isNormalLockOrder(solution, o1, o2));
    }
}
