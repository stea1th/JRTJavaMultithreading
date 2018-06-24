package com.javarush.task.task21.task2101;



/*
Определяем адрес сети
*/
public class Solution {
    public static void main(String[] args) {
        byte[] ip = new byte[]{(byte) 192, (byte) 168, 1, 2};
        byte[] mask = new byte[]{(byte) 255, (byte) 255, (byte) 254, 0};
        byte[] netAddress = getNetAddress(ip, mask);
        print(ip);          //11000000 10101000 00000001 00000010
        print(mask);        //11111111 11111111 11111110 00000000
        print(netAddress);  //11000000 10101000 00000000 00000000
    }

    public static byte[] getNetAddress(byte[] ip, byte[] mask) {

        byte[] net = new byte[4];
        for (int i = 0; i <net.length ; i++) {
            net[i] = (byte)(ip[i]&mask[i]);
        }
        return net;
    }

    public static void print(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes){
            String line = "";
            int x = b<0? b+256 : b;
            for (int i = 7; i >-1 ; i--) {
                line +=(x>>i)%2;
            }
            builder = builder.append(line).append(" ");
        }
        System.out.println(builder.toString());
    }
}
