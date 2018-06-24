package com.javarush.task.task22.task2211;

import java.io.*;
import java.nio.charset.Charset;

/* 
Смена кодировки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        FileInputStream input = new FileInputStream(args[0]);
        FileOutputStream output = new FileOutputStream(args[1]);

        Charset utf8 = Charset.forName("UTF-8");
        Charset win1251 = Charset.forName("Windows-1251");
        byte[] b = new byte[input.available()];
        input.read(b);
        String s = new String(b, win1251);
        b = s.getBytes(utf8);
        output.write(b);
        input.close();
        output.close();
    }
}
