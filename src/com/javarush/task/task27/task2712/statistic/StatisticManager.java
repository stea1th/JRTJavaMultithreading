package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StatisticManager {



    private StatisticStorage statisticStorage = new StatisticStorage();

    private static StatisticManager ourInstance = new StatisticManager();

    public static StatisticManager getInstance() {
        return ourInstance;
    }

    private StatisticManager() {


    }



    public TreeMap<Date, Long> computeAdvertisementProfit() {
        Map<EventType, List<EventDataRow>> myStorage = statisticStorage.getStorage();
        List<EventDataRow> videoList  = new ArrayList<>();
        for(Map.Entry<EventType, List<EventDataRow>> entry : myStorage.entrySet()){
            if(entry.getKey().equals(EventType.SELECTED_VIDEOS)){
                videoList = entry.getValue();
            }
        }
        TreeMap<Date, Long> resultMap = new TreeMap<>(Collections.reverseOrder());
        String date = "";
        Long totalProfit = 0L;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        for(EventDataRow event : videoList){
            VideoSelectedEventDataRow videoEvent = (VideoSelectedEventDataRow) event;
            if(!date.equals(formatter.format(videoEvent.getDate()))){
                date = formatter.format(videoEvent.getDate());
                totalProfit =videoEvent.getAmount();

            }else{
                totalProfit+=videoEvent.getAmount();
            }

            try {
                resultMap.put(formatter.parse(date), totalProfit);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        //SimpleDateFormat formatterX = new SimpleDateFormat("yyyy-MM-dd");



            /*resultMap.put("12-May-2013", 54398L);
            resultMap.put("14-May-2013", 250L);
            resultMap.put("13-May-2013", 102L);*/



        return resultMap;
    }

    public TreeMap<Date, TreeMap<String, Integer>> computeCookWorkloading(){
        Map<EventType, List<EventDataRow>> myStorage = statisticStorage.getStorage();
        List<EventDataRow> cooks  = new ArrayList<>();
        for(Map.Entry<EventType, List<EventDataRow>> entry : myStorage.entrySet()){
            if(entry.getKey().equals(EventType.COOKED_ORDER)){
                cooks = entry.getValue();
            }
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        String date = "";
        String name = "";
        Integer totalCookingDuration = 0;
        TreeMap<Date, TreeMap<String, Integer>> resultMap = new TreeMap<>(Collections.reverseOrder());
        TreeMap<String, Integer>map = new TreeMap();
        for(EventDataRow event : cooks) {
            CookedOrderEventDataRow cookEvent = (CookedOrderEventDataRow) event;

            //ConsoleHelper.writeMessage(cookEvent.getCookName());
            name = cookEvent.getCookName();
            if (!date.equals(formatter.format(cookEvent.getDate()))) {
                map = new TreeMap<>();
                date = formatter.format(cookEvent.getDate());
                totalCookingDuration = (int) Math.ceil(cookEvent.getTime() / 60);
            }else {
                if (map.containsKey(name))
                    totalCookingDuration = map.get(name) + (int) Math.ceil(cookEvent.getTime() / 60);
                else
                    totalCookingDuration = (int) Math.ceil(cookEvent.getTime() / 60);
            }

            map.put(name, totalCookingDuration);

                //if (!name.equals(cookEvent.getCookName())) {

            try {
                resultMap.put(formatter.parse(date), map);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        //}
        //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        /*try {
            map = new TreeMap<>();
            map.put("Ivanov", 60);
            map.put("Petrov", 35);
            resultMap.put(formatter.parse("2013-05-14"), map);
            map = new TreeMap<>();
            map.put("Ivanov", 129);
            resultMap.put((formatter.parse("2013-05-13")), map);
            map = new TreeMap<>();
            map.put("Ivanov", 6);
            map.put("Petrov", 5);
            resultMap.put((formatter.parse("2013-05-12")), map);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        return resultMap;
    }



    public void register(EventDataRow data){
        statisticStorage.put(data);
    }



    private static class StatisticStorage {

        private Map <EventType, List<EventDataRow>> storage;

        private StatisticStorage() {
            storage = new HashMap<>();
            for(EventType event : EventType.values()){
                storage.put(event, new ArrayList<EventDataRow>());
            }
        }

        private void put(EventDataRow data){
            for(Map.Entry<EventType, List<EventDataRow>> entry : storage.entrySet()){
                if(data.getType().equals(entry.getKey())){
                    entry.getValue().add(data);
                }
            }
        }

        private Map<EventType, List<EventDataRow>> getStorage() {
            return storage;
        }
    }
}
