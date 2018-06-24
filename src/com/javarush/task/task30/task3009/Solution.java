package com.javarush.task.task30.task3009;

import java.util.HashSet;
import java.util.Set;

/* 
Палиндром?
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getRadix("112"));        //expected output: [3, 27, 13, 15]
        System.out.println(getRadix("123"));        //expected output: [6]
        System.out.println(getRadix("5321"));       //expected output: []
        System.out.println(getRadix("1A"));         //expected output: []
    }
    private static Set<Integer> getRadix(String number) {
        Set<Integer> set = new HashSet<>();
        StringBuilder builder;
        try {
            for (int i = 2; i < 37; i++) {
                builder = new StringBuilder();
                int x = Integer.parseInt(number);
                String digit = Integer.toUnsignedString(x, i);
                builder.append(digit.substring(digit.length() / 2));
                builder.reverse();
                int sizeOne = digit.length() % 2 == 0 ? digit.length() / 2 : digit.length() / 2 + 1;
                if (digit.substring(0, sizeOne).equals(builder.toString())) {
                    set.add(i);
                }

                //set.add(Integer.parseInt(number, i));
            }
        } catch (NumberFormatException e) {

        }
        return set;

    }
}