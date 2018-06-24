package com.javarush.task.task27.task2712.kitchen;

public enum Dish {
    Fish(25),
    Steak(30),
    Soup(15),
    Juice(5),
    Water(3);

    private int duration;


    Dish(int i) {
        duration=i;
    }

    public static String allDishesToString(){
        StringBuilder builder = new StringBuilder();
        Dish[] list = Dish.values();
        for(Dish dish : list){
            builder.append(dish.name()).append(", ");
        }
        return builder.toString().substring(0, builder.length()-2);
    }

    public double getDuration() {
        return duration;
    }
}
