package com.javarush.task.task27.task2712.ad;

public class Advertisement {

    private Object content;
    private String name;
    private long initialAmount;
    private int hits;
    private int duration;

    private long amountPerOneDisplaying;
    private int amountPerOneSecond;

    public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;
        if(hits >0)
            amountPerOneDisplaying = initialAmount/hits;
        amountPerOneSecond =(int)((((double)amountPerOneDisplaying/duration))*1000);
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public long getAmountPerOneDisplaying() {
        return amountPerOneDisplaying;
    }

    public int getAmountPerOneSecond() {
        return amountPerOneSecond;
    }

    public void revalidate(){

        if(hits<=0)
            throw new NoVideoAvailableException();
        hits--;
        /*if (hits == 1)
            amountPerOneDisplaying += initialAmount % amountPerOneDisplaying;*/
    }

    public int getHits() {
        return hits;
    }
}
