package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConsoleHelper {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString()throws IOException{
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder()throws IOException{
        List<Dish> dishesForOrder = new ArrayList<>();
        Dish[] allDishes = Dish.values();
        writeMessage(Dish.allDishesToString());
        writeMessage("Введите желаемое блюдо: ");
        String myDish = "";
        while(true){
            if((myDish=readString()).equalsIgnoreCase("exit"))
                break;

            for(Dish dish : allDishes){
                if(dish.name().equalsIgnoreCase(myDish)) {
                    dishesForOrder.add(dish);
                    break;
                }
            }
            if(!dishesForOrder.toString().toLowerCase().contains(myDish))
                writeMessage("К сожалению такого блюда нет. Попробуйте что-нибудь другое.");
        }
        return dishesForOrder;
    }
}
