package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementStorage {

    private final List<Advertisement> videos = new ArrayList<>();

    private static AdvertisementStorage ourInstance = new AdvertisementStorage();

    public static AdvertisementStorage getInstance() {
        return ourInstance;
    }

    private AdvertisementStorage() {
        Object someContent = new Object();
        videos.add(new Advertisement(someContent, "First Video", 5000, 100, 3*60));
        videos.add(new Advertisement(someContent, "Second Video", 100, 10, 15*60));
        videos.add(new Advertisement(someContent, "Third Video", 400, 0, 10*60));
        videos.add(new Advertisement(someContent, "Fourth Video", 1000, 12, 5*60));
        videos.add(new Advertisement(someContent, "Fifth Video", 2500, 20, 3*60));
        videos.add(new Advertisement(someContent, "седьмой видосик", 6000, 140, 7*60));
        videos.add(new Advertisement(someContent, "Seventh Video", 360, 2, 8*60));
        videos.add(new Advertisement(someContent, "Eighth Video", 5000, 90, 3*60));
        videos.add(new Advertisement(someContent, "Девятое видео", 400, 3, 10*60));
    }

    public List<Advertisement> list(){
        return videos;
    }

    public void add(Advertisement advertisement){
        videos.add(advertisement);
    }


}
