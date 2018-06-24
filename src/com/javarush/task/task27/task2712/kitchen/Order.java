package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private final Tablet tablet;

    protected List<Dish> dishes;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        //this.dishes = ConsoleHelper.getAllDishesForOrder();
        this.initDishes();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("");
        if(!dishes.isEmpty()) {
            for (Dish dish : dishes) {
                builder.append(dish).append(", ");
            }
            return "Your order: ["+  builder.toString().substring(0, builder.length()-2)  +"] of "+tablet.toString();
        }

        return "Your order: ["+ builder +"] of "+tablet.toString();

        //return dishes.isEmpty() ? "" : "Your order: " + dishes + " of " + tablet;

    }

    public int getTotalCookingTime(){
        int result = 0;
        for(Dish dish : dishes){
            result+=dish.getDuration();
        }
        return result;

    }

    public boolean isEmpty(){
        return dishes.isEmpty();
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public Tablet getTablet() {
        return tablet;
    }

    protected List<Dish> initDishes()throws IOException{
       return dishes = ConsoleHelper.getAllDishesForOrder();
    }
}
