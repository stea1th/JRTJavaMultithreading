package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

public class AdvertisementManager {

    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();

    private int timeSeconds;

    private int n, k;
    private Integer[] a;

    private List<String> allVariants = new ArrayList<>();
    private List<Advertisement> allAvailableVideos = new ArrayList<>();
    private List<Advertisement> bestVideos = new ArrayList<>();

    private int maxCost =0;
    private int maxDuration=0;


    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos()throws NoVideoAvailableException{
        if (storage.list().size()==0){
            throw new NoVideoAvailableException();
        }

        for(Advertisement advertisement : storage.list()){
            if(advertisement.getHits()>0){
                allAvailableVideos.add(advertisement);
            }
        }

        //allAvailableVideos = storage.list();

        Collections.sort(allAvailableVideos, new Comparator<Advertisement>() {
                    @Override
                    public int compare(Advertisement o1, Advertisement o2) {

                        return (int) ((o1.getAmountPerOneDisplaying() != o2.getAmountPerOneDisplaying())?
                                 o2.getAmountPerOneDisplaying()-o1.getAmountPerOneDisplaying() :
                                o1.getAmountPerOneSecond() - o2.getAmountPerOneSecond());
                    }
                });

        //ConsoleHelper.writeMessage("Cooking Duration "+timeSeconds );


        Integer[]result = getBestResult();

        for (int i = 0; i <result.length ; i++) {
            bestVideos.add(allAvailableVideos.get(result[i]));
        }

        StatisticManager.getInstance().register(new VideoSelectedEventDataRow(bestVideos, maxCost, maxDuration));

        for (Advertisement advertisement : bestVideos) {
            if(advertisement.getHits()>0) {
                ConsoleHelper.writeMessage(advertisement.getName() + " is displaying... " + advertisement.getAmountPerOneDisplaying() + ", " + advertisement.getAmountPerOneSecond());
                advertisement.revalidate();
            }
        }
        //ConsoleHelper.writeMessage(maxCost+"ct "+maxDuration+"sec");


    }

    private Integer[] getBestResult(){
        computeAllVariants();

        Integer[] result = new Integer[allAvailableVideos.size()];
        for(String variant : allVariants) {
            String[] x = variant.replace("[", "").replace("]", "").trim().split(", ");
            Integer[] var = new Integer[x.length];
            for (int i = 0; i < var.length; i++) {
                var[i] = Integer.parseInt(x[i].trim())-1;
            }
            int totalDuration = 0;
            int totalCost = 0;
            for (int i = 0; i < var.length; i++) {
                totalDuration += allAvailableVideos.get(var[i]).getDuration();
            }
            if (totalDuration <= timeSeconds) {
                for (int i = 0; i < var.length; i++) {
                    totalCost += allAvailableVideos.get(var[i]).getAmountPerOneDisplaying();                                      //.getAmountPerOneSecond() * allAvailableVideos.get(var[i]).getDuration();
                }
                if (totalCost > maxCost) {
                    maxCost = totalCost;
                    maxDuration = totalDuration;
                    result = var;
                } else if (totalCost == maxCost) {
                    if (totalDuration > maxDuration) {
                        maxDuration = totalDuration;
                        result = var;
                    } else if (totalDuration == maxDuration) {
                        if (var.length < result.length) {
                            result = var;
                        }
                    }
                }
            }
        }
        return result;
    }

    private void computeAllVariants(){
        a = new Integer[allAvailableVideos.size()];

        for (int i = 0; i <allAvailableVideos.size() ; i++) {
            a[i]=i;
        }

        n = a.length;
        while(a.length!=0){
            k = a.length;
            p1(0,0);
            Integer[] b = a;
            a = new Integer[b.length-1];
            for (int i = 0; i < a.length; i++) {
                a[i] = b[i];
            }
        }
    }

    private void p1(int pos, int maxUsed) {
        if (pos == k) {
            //System.out.println(Arrays.toString(a));
            allVariants.add(Arrays.toString(a));
        } else {
            for(int i = maxUsed+1; i <= n; i++) {
                a[pos] = i;
                p1(pos+1,i);
            }
        }
    }

    public int getMaxCost() {
        return maxCost;
    }

    public int getMaxDuration() {
        return maxDuration;
    }
}

    /*public List<Advertisement> compareAndChoose(List<Advertisement> x){

        int maxCost =0;
        int maxDuration =0;
        List<Advertisement> bestList = new ArrayList<>();
        List<Advertisement> list;
        List<Advertisement>allAvailableVideos = x;

        while(allAvailableVideos.size()>0){
            list= chooseBestOption(allAvailableVideos);
            int totalCost = 0;
            int totalDuration = 0;

            for(Advertisement advertisement : list){
                totalCost+=advertisement.getAmountPerOneSecond()*advertisement.getDuration();
                totalDuration+=advertisement.getDuration();
            }
            if(totalCost>maxCost){
                maxCost = totalCost;
                maxDuration = totalDuration;
                bestList = list;
            }else if(totalCost == maxCost){
                if(totalDuration>maxDuration){
                    maxDuration=totalDuration;
                    bestList = list;
                }else if(totalDuration==maxDuration){
                    if(list.size()<bestList.size()){
                        bestList = list;
                    }
                }
            }
            allAvailableVideos.remove(0);*/

            /*ConsoleHelper.writeMessage("----------------------------------------------------------\n");

            for(Advertisement advertisement : list){
                ConsoleHelper.writeMessage(advertisement.getName()+" "+advertisement.getAmountPerOneDisplaying()+" ct/hit "+advertisement.getAmountPerOneSecond()+" ct/sec "+advertisement.getDuration()+" sec");
            }
            ConsoleHelper.writeMessage("TotalCost "+totalCost+"ct, TotalDuration "+totalDuration+"sec "+"TotalSize "+list.size());*/
        /*}
        return bestList;
    }

    public List<Advertisement> chooseBestOption(List<Advertisement> list){
        int variableTimeSeconds = timeSeconds;
        //int costsAllViews = 0;
        List<Advertisement> result = new ArrayList<>();
        for(Advertisement advertisement : list){
            if(advertisement.getDuration()<variableTimeSeconds){
                variableTimeSeconds-=advertisement.getDuration();
                //costsAllViews+= advertisement.getAmountPerOneDisplaying();
                result.add(advertisement);
            }
        }
        return result;
    }*/



/*List<Advertisement> allAvailableVideos = new ArrayList<>();
        for(Advertisement advertisement : storage.list()){
            if(advertisement.getHits()>0){
                allAvailableVideos.add(advertisement);
            }
        }*/

        /*Collections.sort(allAvailableVideos, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {

                return (int)(o2.getAmountPerOneDisplaying()-o1.getAmountPerOneDisplaying());
                //return o2.getAmountPerOneSecond()-o1.getAmountPerOneSecond();
            }
        });*/

//ConsoleHelper.writeMessage("Cooking Duration "+timeSeconds );

//List<Advertisement> bestList = compareAndChoose(allAvailableVideos);

//ConsoleHelper.writeMessage("-------------------------------------");

        /*for(Advertisement advertisement : bestList){
            //ConsoleHelper.writeMessage(advertisement.getName()+" "+advertisement.getAmountPerOneDisplaying()+" ct/hit "+advertisement.getAmountPerOneSecond()+" ct/sec "+advertisement.getDuration()+" sec");
            ConsoleHelper.writeMessage(advertisement.getName()+" is displaying... "+advertisement.getAmountPerOneDisplaying()+", "+advertisement.getAmountPerOneSecond());
            advertisement.revalidate();
        }*/
        /*ConsoleHelper.writeMessage("MaxCost "+maxCost+"ct, MaxlDuration "+maxDuration+"sec "+"MaxlSize "+bestList.size());

        ConsoleHelper.writeMessage("-------------------------------------");*/


        /*ConsoleHelper.writeMessage("Cooking Duration "+timeSeconds );
        ConsoleHelper.writeMessage("-----------------------------------");

        for(Advertisement advertisement : bestList){
            ConsoleHelper.writeMessage();
            //ConsoleHelper.writeMessage(adveisement.getName()+" "+advertisement.getAmountPerOneDisplaying()+" ct/hit "+advertisement.getAmountPerOneSecond()+" ct/sec "+advertisement.getDuration()+" sec"rt);
        }*/
