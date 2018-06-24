package com.javarush.task.task30.task3002;

import jdk.nashorn.internal.runtime.options.Option;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/*
Осваиваем методы класса Integer
*/
public class Solution {

    public static void main(String[] args) {
        System.out.println(convertToDecimalSystem("0x16")); //22
        System.out.println(convertToDecimalSystem("012"));  //10
        System.out.println(convertToDecimalSystem("0b10")); //2
        System.out.println(convertToDecimalSystem("62"));   //62
    }

    public static String convertToDecimalSystem(String s) {
        Boolean b = s.chars()
                .anyMatch(Character::isLetter);

        if(s.contains("x")){
            String result = s.substring(s.indexOf("x")+1, s.length());
            int x = Integer.parseInt(result, 16);
            /*int sum = 0;
            for (int i = 0; i <result.length() ; i++) {
                sum+=(int)(Math.pow(16, i)*(x%10) );
                x=x/10;
            }*/
            return String.valueOf(x);
        } else if(s.contains("b")){
            String result = s.substring(s.indexOf("b")+1, s.length());
            int x = Integer.parseInt(result, 2);
            /*int sum = x%10;
            for (int i = 1; i <result.length() ; i++) {
                x=x/10;
                sum+=(int)(Math.pow(2, i)*(x%10));
            }*/
            return String.valueOf(x);
        } else if(!b&&s.startsWith("0")){
            int x = Integer.parseInt(s, 8);
            /*int sum = x%10;
            int i = 0;
            while(x>0){
                ++i;
                x=x/10;
                sum+=(int)(Math.pow(8, i)*(x%10));

            }*/
            return String.valueOf(x);
        }
        return s;

    }

}
