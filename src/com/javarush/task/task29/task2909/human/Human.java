package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Human implements Alive {
    private List<Human> children = new ArrayList<>();
    private static int nextId = 0;
    private int id;
    protected int age;
    protected String name;

    protected Size size;
    private BloodGroup bloodGroup;

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    /*public void setBloodGroup(int code) {
        switch(code){
            case 1 :
                bloodGroup=BloodGroup.first();
                break;
            case 2 :
                bloodGroup=BloodGroup.second();
                break;
            case 3 :
                bloodGroup=BloodGroup.third();
                break;
            case 4 :
                bloodGroup=BloodGroup.fourth();
                break;
        }

    }*/

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }


    public Human(String name, int age) {
        this.name = name;
        this.age = age;
        nextId++;
    }

    public List<Human> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public void addChild(Human human ){
        children.add(human);
    }

    public void removeChild(Human human){
        children.remove(human);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition(){
        return "Человек";
    }

    public void live() {
    }

    public int getId() {
        return this.id = nextId;
    }

    public void printSize() {
        System.out.println("Рост: " + size.height + " Вес: " + size.weight);
    }

    public void printData() {
        System.out.println(getPosition()+": "+name);
    }

    public class Size{
        public int height;
        public int weight;
    }
}