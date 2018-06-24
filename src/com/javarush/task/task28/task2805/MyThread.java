package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread {
    private  final static AtomicInteger x = new AtomicInteger(0);
    static{
        Thread.currentThread().setPriority((x.get()<10)?x.addAndGet(1):x.addAndGet(-9));
    }

    public MyThread() {
        Thread.currentThread().setPriority((x.get()<10)?x.addAndGet(1):x.addAndGet(-9));


    }

    public MyThread(Runnable target) {
        super(target);
        Thread.currentThread().setPriority((x.get()<10)?x.addAndGet(1):x.addAndGet(-9));
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        Thread.currentThread().setPriority((x.get()<10)?x.addAndGet(1):x.addAndGet(-9));
    }

    public MyThread(String name) {
        super(name);
        Thread.currentThread().setPriority((x.get()<10)?x.addAndGet(1):x.addAndGet(-9));
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        Thread.currentThread().setPriority((x.get()<10)?x.addAndGet(1):x.addAndGet(-9));


    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        Thread.currentThread().setPriority((x.get()<10)?x.addAndGet(1):x.addAndGet(-9));
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        Thread.currentThread().setPriority((x.get()<10)?x.addAndGet(1):x.addAndGet(-9));
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        Thread.currentThread().setPriority((x.get()<10)?x.addAndGet(1):x.addAndGet(-9));
    }
}
