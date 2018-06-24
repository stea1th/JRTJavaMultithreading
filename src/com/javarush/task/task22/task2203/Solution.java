package com.javarush.task.task22.task2203;

/* 
Между табуляциями
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
        if (string!=null) {
            int start = string.indexOf("\t")+1;
            int end = string.indexOf("\t", start);
            if (start==0||end==-1){
                throw new TooShortStringException();
            }else return string.substring(start, end);
        }
        throw new TooShortStringException();
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }
}
