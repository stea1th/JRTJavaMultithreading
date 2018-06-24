package com.javarush.task.task22.task2212;


/*
Проверка номера телефона
*/
public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        if(telNumber!="" &&telNumber!=null) {
            StringBuilder builder = new StringBuilder(telNumber);
            if (builder.charAt(0) == '+' && telNumber.replaceFirst("\\(", "").replaceFirst("\\)", "").replace("-", "").replaceFirst("\\+", "").length() == 12) {
                String ptrn3 = "\\+\\d+(\\(\\d{3}\\))?\\d+\\-?\\d+\\-?\\d+";
                return telNumber.matches(ptrn3);
            } else if (telNumber.replaceFirst("\\(", "").replaceFirst("\\)", "").replace("-", "").length() == 10) {
                String ptrn4 = "\\d*(\\(\\d{3}\\))?\\d+\\-?\\d+\\-?\\d+";
                return telNumber.matches(ptrn4);
            } else
                return false;
        }else return false;
    }

    public static void main(String[] args) {
        System.out.println(checkTelNumber("+380501234567"));
        System.out.println(checkTelNumber("+38((050))1234567"));
        System.out.println(checkTelNumber("+38050123-45-67"));
        System.out.println(checkTelNumber("050123-4567"));
        System.out.println(checkTelNumber("+38)050(1234567"));
        System.out.println(checkTelNumber("+38(050)1-23-45-6-7"));
        System.out.println(checkTelNumber("050ххх4567"));
        System.out.println(checkTelNumber("050123456"));
        System.out.println(checkTelNumber("(0)501234567"));
        System.out.println(checkTelNumber(null));
    }
}
