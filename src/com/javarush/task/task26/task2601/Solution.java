package com.javarush.task.task26.task2601;

import java.lang.reflect.Array;
import java.util.*;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {
        Integer[] array = {13, 8, 15, 5, 17};
        sort(array);


    }

    public static Integer[] sort(Integer[] array) {
        //implement logic here
        Collections.sort(Arrays.asList(array));
        int finalMediana;
        if(array.length%2==1){
            finalMediana = array[array.length/2];
        }else
            finalMediana=(array[array.length/2-1]+array[array.length/2])/2;

        Arrays.asList(array).sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                o1 = Math.abs(finalMediana-o1);
                o2 = Math.abs(finalMediana-o2);
                return o1-o2;
            }
        });
        return array;
    }
}
