package com.javarush.task.task25.task2514;

/* 
Первый закон Финэйгла: если эксперимент удался, что-то здесь не так...
*/
public class Solution {
    public static class YieldRunnable implements Runnable {
        private int index;

        public YieldRunnable(int index) {
            this.index = index;
        }

        public void run() {
            System.out.println("begin-" + index);
            Thread.yield();
            System.out.println("end-" + index);
        }
    }

    public static void main(String[] args) {
        Solution.YieldRunnable yieldRunnable = new Solution.YieldRunnable(1);
        Solution.YieldRunnable yieldRunnable1 = new Solution.YieldRunnable(2);
        Solution.YieldRunnable yieldRunnable2 = new Solution.YieldRunnable(3);
        Thread thread = new Thread(yieldRunnable);
        Thread thread1 = new Thread(yieldRunnable1);
        Thread thread2 = new Thread(yieldRunnable2);
        thread.start();
        thread1.start();
        thread2.start();
    }
}
