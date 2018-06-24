package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.TestOrder;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet  {

    final int number;

    private LinkedBlockingQueue<Order> queue;

    private Order order;

    private static Logger logger = Logger.getLogger(Tablet.class.getName());

    public Tablet(int number) {
        this.number = number;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    public void createTestOrder() {
        TestOrder order = null;
        try {
            order = new TestOrder(this);
            getOrder(order);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
    }

    public void createOrder(){
        Order order = null;
        try{
            order = new Order(this);
            getOrder(order);
        } catch (IOException e) {

        }
    }

    private void getOrder(Order order) {
        try {
            ConsoleHelper.writeMessage(order.toString());
            if (!order.isEmpty()) {
                queue.add(order);
                AdvertisementManager manager = new AdvertisementManager(order.getTotalCookingTime() * 60);
                manager.processVideos();


            }
        } catch (NoVideoAvailableException e){
            logger.log(Level.INFO, "No video is available for the order "+order);
        }
    }

    @Override
    public String toString()  {
        return "Tablet{" +
                    "number=" + number +
                    '}';

    }
}
