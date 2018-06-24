package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/


import java.util.Arrays;

import java.util.OptionalInt;

public class Solution {
    public static void main(String[] args) {

        try {
            if(!args[0].chars()
                    .allMatch(Character::isLetterOrDigit))
                System.out.println("incorrect");
                else{
                int[] array = args[0].chars()
                        .map(Character::getNumericValue)
                        .toArray();
                OptionalInt opt = Arrays.stream(array)
                        .max();
                System.out.println(opt.getAsInt()<2? 2 : opt.getAsInt()+1);
            }

        } catch (Exception e) {
            System.out.println("WTF");
        }
    }
}