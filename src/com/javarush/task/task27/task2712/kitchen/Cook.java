package com.javarush.task.task27.task2712.kitchen;



import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Restaurant;
import com.javarush.task.task27.task2712.Tablet;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;

import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Cook extends Observable implements Runnable {

    private String name;
    private boolean busy = false;
    private boolean stopped = false;

    private LinkedBlockingQueue<Order> queue;


    public Cook(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    public void startCookingOrder(Order order){
        busy = true;
        Tablet tablet = order.getTablet();
        StatisticManager.getInstance().register(new CookedOrderEventDataRow(tablet.toString(), this.name, order.getTotalCookingTime()*60, order.getDishes()));
        ConsoleHelper.writeMessage("Start cooking - "+order+", cooking time "+order.getTotalCookingTime()+"min");
        setChanged();
        notifyObservers(order);
        try {
            Thread.sleep(order.getTotalCookingTime()*10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        busy = false;
    }

    public boolean isBusy() {
        return busy;
    }


    @Override
    public void run() {
        while (!stopped) {
            try {
                Thread.sleep(10);                                          //TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                return;
            }
            if (queue.peek() != null) {
                if (!this.isBusy()) {
                    Order order = queue.poll();
                    if (order != null)
                        this.startCookingOrder(order);
                }
            }
            if (queue.isEmpty())
                stopped = true;


        }
    }
}
