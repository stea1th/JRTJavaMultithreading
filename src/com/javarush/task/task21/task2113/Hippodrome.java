package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {

    private List<Horse> horses = new ArrayList<>();
    static Hippodrome game = null;

    public List<Horse> getHorses() {
        return horses;
    }

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public static void main(String[] args) {

        game = new Hippodrome(new ArrayList<Horse>());
        Horse firstHorse = new Horse("Vadia", 3, 0);
        Horse secondHorse = new Horse("Ania", 3, 0);
        Horse thirdHorse = new Horse("Dima", 3, 0);
        game.getHorses().add(firstHorse);
        game.getHorses().add(secondHorse);
        game.getHorses().add(thirdHorse);
        try {
            game.run();
        }catch (InterruptedException e){

        }
        game.printWinner();
    }

    public void run()throws InterruptedException{
        for (int i = 1; i <101 ; i++) {
            move();
            print();
            Thread.sleep(200);
        }
    }

    public void move(){
        for(Horse horse : horses){
            horse.move();
        }
    }

    public void print(){
        for(Horse horse : horses){
            horse.print();
        }

        for (int i = 0; i <10 ; i++) {
            System.out.println();
        }
    }

    public Horse getWinner(){
        double longestDistance = 0;
        Horse winnerHorse = null;
        for (Horse horse : horses){
            if(horse.getDistance()>longestDistance){
                longestDistance = horse.getDistance();
                winnerHorse = horse;
            }else continue;
        }
        return winnerHorse;
    }

    public void printWinner(){
        System.out.println("Winner is "+getWinner().getName()+"!");
        for (int i = 0; i <10 ; i++) {
            System.out.println();
        }
    }
}
