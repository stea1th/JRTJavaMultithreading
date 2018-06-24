package com.javarush.task.task29.task2907;

import java.math.BigDecimal;

/* 
Этот странный BigDecimal
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getValue(1.1d, 1.2d));
    }

    public static BigDecimal getValue(double v1, double v2) {
        String V1 = ""+v1;
        String V2 = ""+v2;
        return new BigDecimal(V1).add(new BigDecimal(V2));
    }
}