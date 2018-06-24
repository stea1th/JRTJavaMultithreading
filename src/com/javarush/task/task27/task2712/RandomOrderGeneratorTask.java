package com.javarush.task.task27.task2712;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class RandomOrderGeneratorTask implements Runnable {

    private List<Tablet> tablets;

    private int interval;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval) {
        this.tablets = tablets;
        this.interval = interval;

    }

    @Override
    public void run() {
        Tablet tablet;
        while(!Thread.currentThread().isInterrupted()) {
            if (tablets.size() > 0) {
                tablet = tablets.get((int) ((Math.random() * tablets.size() - 1) + 1));
                tablet.createTestOrder();
                try {
                    TimeUnit.MILLISECONDS.sleep(interval);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

}
