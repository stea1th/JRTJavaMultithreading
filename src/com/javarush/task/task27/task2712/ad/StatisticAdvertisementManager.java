package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StatisticAdvertisementManager {

    private static StatisticAdvertisementManager ourInstance = new StatisticAdvertisementManager();

    private AdvertisementStorage storage = AdvertisementStorage.getInstance();

    private List<Advertisement> storageContent = storage.list();

    public static StatisticAdvertisementManager getInstance() {
        return ourInstance;
    }

    private StatisticAdvertisementManager() {
    }

    public List<Advertisement> getActiveVideos(){
        List<Advertisement> positiveList = new ArrayList<>();
        for(Advertisement advertisement : storageContent){
            if(advertisement.getHits()>0){
                positiveList.add(advertisement);
            }
        }
        Collections.sort(positiveList, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        return positiveList;
    }

    public List<Advertisement> getInactiveVideos(){
        List<Advertisement> negativeList = new ArrayList<>();
        for(Advertisement advertisement : storageContent){
            if(advertisement.getHits()==0){
                negativeList.add(advertisement);
            }
        }
        Collections.sort(negativeList, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        return negativeList;
    }


}
