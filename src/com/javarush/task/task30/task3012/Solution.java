package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

import java.util.ArrayList;
import java.util.List;


public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(463);
    }

    public void createExpression(int number) {
        //напишите тут ваш код
        int number1 = number;
        List<String> signs = new ArrayList<>();
        while(number>0){
            int rest = number%3;
            number /=3;
            signs.add(rest==2? "-" : rest==1? "+" : "0");
            if (rest == 2){
                number++;
            }
        }
        if(signs.get(signs.size()-1).equals("-")){
            signs.add("+");
        }
        //Optional<String> opt =  signs.stream()
               // .reduce((s1, s2)-> s1+s2);
        StringBuilder builder = new StringBuilder(number1+" =");
        //System.out.println(builder.reverse());
        for (int i = 0; i <signs.size() ; i++) {
            int x =(int) Math.pow(3, i);
            if(!signs.get(i).equals("0")){
                builder.append(" ").append(signs.get(i)).append(" ").append(x);
            }
        }
        System.out.println(builder.toString());


    }
}