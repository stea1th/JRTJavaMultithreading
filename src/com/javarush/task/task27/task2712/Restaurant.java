package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {

    private static final int ORDER_CREATING_INTERVAL = 100;

    private static final LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args)throws IOException {


            Cook cook1 = new Cook("Vadim");
            cook1.setQueue(orderQueue);
            Cook cook2 = new Cook("Anton");
            cook2.setQueue(orderQueue);
            Thread thr1 = new Thread(cook1);
            Thread thr2 = new Thread(cook2);
            //thr1.setDaemon(true);
            //thr2.setDaemon(true);



            Waiter waiter = new Waiter();
            cook1.addObserver(waiter);
            cook2.addObserver(waiter);
            Tablet tablet = null;

            List<Tablet> tablets = new ArrayList<>();
            for (int i = 0; i <5 ; i++) {
                tablet = new Tablet(i);
                tablet.setQueue(orderQueue);
                tablets.add(tablet);
            }
            thr1.start();
            thr2.start();



            Thread thread = new Thread(new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL));
            thread.start();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }

            thread.interrupt();


            DirectorTablet directorTablet = new DirectorTablet();
            directorTablet.printAdvertisementProfit();
            directorTablet.printCookWorkloading();
            directorTablet.printActiveVideoSet();
            directorTablet.printArchivedVideoSet();


        }

    public static int getOrderCreatingInterval() {
        return ORDER_CREATING_INTERVAL;
    }

}
