package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DirectorTablet {

    public void printAdvertisementProfit(){
        TreeMap<Date, Long> resultMap = StatisticManager.getInstance().computeAdvertisementProfit();
        Double total = 0.0;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        for(Map.Entry<Date, Long> entry : resultMap.entrySet()){
            ConsoleHelper.writeMessage(String.format("%s - %.2f",formatter.format(entry.getKey()),(double)entry.getValue()/100).replace(",", "."));
            total+=(double)entry.getValue()/100;
        }
        ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "Total - %.2f",total).replace(",", "."));
    }

    public void printCookWorkloading(){
        TreeMap<Date, TreeMap<String, Integer>> resultMap = StatisticManager.getInstance().computeCookWorkloading();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        for(Map.Entry<Date, TreeMap<String, Integer>> entry : resultMap.entrySet()){
            ConsoleHelper.writeMessage("");
            ConsoleHelper.writeMessage(formatter.format(entry.getKey()));
            for(Map.Entry<String, Integer> mapEntry : entry.getValue().entrySet()){
                ConsoleHelper.writeMessage(mapEntry.getKey()+" - "+mapEntry.getValue()+" min");
            }

        }
    }

    public void printActiveVideoSet(){
        for(Advertisement advertisement : StatisticAdvertisementManager.getInstance().getActiveVideos()){
            ConsoleHelper.writeMessage(String.format("%s - %d", advertisement.getName(), advertisement.getHits()));
        }

    }

    public void printArchivedVideoSet(){
        for(Advertisement advertisement : StatisticAdvertisementManager.getInstance().getInactiveVideos()){
            ConsoleHelper.writeMessage(String.format("%s", advertisement.getName()));
        }
    }
}
