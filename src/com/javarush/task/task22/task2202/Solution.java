package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush — "));
    }

    public static String getPartOfString(String string) {

        /*int count = 0;
        int end = 0;

        int start = string.indexOf(" ") + 1;
        char[] letters = string.toCharArray();
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] == 32)
                count++;
            if (count == 4) {
                string += " ";
                end = string.indexOf(" ", i);
            }
        }
        if (count < 4) {
            throw new TooShortStringException();
        }else return string.substring(start, end);
    }*/
        if (string!=null) {
            String[] words = string.split(" ");
            StringBuilder builder = new StringBuilder();
            if (words.length < 5) {
                throw new TooShortStringException();
            } else {
                for (int i = 1; i < 5; i++) {
                    builder.append(words[i]).append(" ");
                }
            }
            return builder.toString().trim();
        }
        throw new TooShortStringException();
    }


    public static class TooShortStringException extends RuntimeException{
    }
}
