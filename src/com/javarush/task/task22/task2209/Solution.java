package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/*
Составить цепочку слов
*/
public class Solution {
    public static void main(String[] args)throws IOException {
        //...
        BufferedReader reader = new BufferedReader(new FileReader(getFileName()));
        String words = "";
        while(reader.ready()){
            words += reader.readLine()+" ";
        }
        String[] objects = words.split(" ");
        objects[0] = objects[0].replaceFirst("\\W", "");
        StringBuilder result = getLine(objects);
        System.out.println(result.toString());
    }

    public static String getFileName(){
        return new Scanner(System.in).nextLine();
    }

    public static StringBuilder getLine(String... words) {

        ArrayList<String> list = new ArrayList<String>(Arrays.asList(words));
        StringBuilder stringBuilder = new StringBuilder();
        Collections.sort(list);
        if (words.length!=0) {
            stringBuilder.append(list.get(0));
            while (list.size() > 1) {
                int lastIndex = stringBuilder.length() - 1;
                String secondWord = "";
                for (int i = 1; i < list.size(); i++) {
                    secondWord = list.get(i);
                    StringBuilder secondBuilder = new StringBuilder(secondWord.toLowerCase());
                    if (stringBuilder.charAt(lastIndex) == secondBuilder.charAt(0)) {
                        stringBuilder.append(" ").append(secondWord);
                        break;
                    }else if (i==list.size()-1){
                        stringBuilder.append(" ").append(secondWord);
                        break;
                    }
                }
                list.remove(secondWord);
                list.set(0, secondWord);
            }
            return stringBuilder;
        }else  return stringBuilder.append("");
    }
}
