package com.javarush.task.task21.task2113;

public class Horse {

    String name;
    double speed;
    double distance;

    public Horse(String name, double speed, double distance) {
        this.name = name;
        this.speed = speed;
        this.distance = distance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDistance() {
        return distance;
    }

    public void move(){
        distance+=speed*Math.random();
    }

    public void print(){
        //StringBuilder strBuild = new StringBuilder();
        String result = "";
        int dots = (int) Math.floor(getDistance());
        for (int i = 0; i <dots ; i++) {
            //strBuild.append(".");
            result+=".";
        }
        //System.out.println(strBuild.append(getName()).toString());
        System.out.println(result+getName());
    }
}
