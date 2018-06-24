package com.javarush.task.task22.task2208;



import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* 
Формируем WHERE
*/
public class Solution {
    public static Map<String, String> map = new LinkedHashMap<>();
    public static void main(String[] args) {
        map.put("name", "Ivanov");
        map.put("country", "Ukraine");
        map.put("age", null);
        System.out.println(getQuery(map));

    }
    public static String getQuery(Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()){
            if ((entry.getValue()!=null)&&(entry.getKey()!=null)){
                if (stringBuilder.length()==0){
                    stringBuilder.append(entry.getKey()+" = ").append("'"+entry.getValue()+"'");
                }else
                    stringBuilder.append(" and ").append(entry.getKey()+" = ").append("'"+entry.getValue()+"'");
            }
        }
        return stringBuilder.toString();
    }
}
