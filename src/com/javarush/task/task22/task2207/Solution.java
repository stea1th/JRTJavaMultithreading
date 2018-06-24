package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args)throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(getFileName()));
        StringBuilder sBuilder = new StringBuilder();

        while(reader.ready()){
            sBuilder.append(reader.readLine()).append(" ");
        }
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(sBuilder.toString().split(" ")));
        while(list.size()>1){
            String x = list.get(0);
            StringBuilder reverse = new StringBuilder(x);
            int n = list.size();
            for (int i = 1; i <n ; i++) {
                String y = list.get(i);
                if (reverse.reverse().toString().equals(y)){
                    result.add(new Pair(x, y));
                    list.remove(i);
                    break;
                }
            }
            list.remove(0);
        }
    }

    public static String getFileName(){
        return new Scanner(System.in).nextLine();
    }

    public static class Pair {
        public Pair() {

        }

        String first;
        String second;

        public Pair(String first, String second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null && second != null ? second :
                    second == null && first != null ? first :
                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
