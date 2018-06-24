package com.javarush.task.task24.task2403;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.server.RemoteObject;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.EventListener;

/* 
Так-с... сопоставим
*/
public class Solution {
    public class Car
    {
        public ArrayList createPoliceCars(int count)
            {
                ArrayList result = new ArrayList();

                for(int i=0; i<count; i++)
                {
                    final int number = i;
                    result.add(new Car()
                    {
                        int policeNumber = number;
                    });
                }
                return result;
            }
        }

    public static void main(String[] args) {
        Car car = new Solution().new Car();
        for (Object o : car.createPoliceCars(5)){
            System.out.println(o);
        }


    }
}
