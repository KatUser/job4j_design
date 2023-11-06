package ru.job4j.io;

import java.io.ByteArrayInputStream;

public class ByteArrayInputStream2 {
    public static void main(String[] args) {
        String str = "123456789";
        byte[] bytes = str.getBytes();
        ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes, 3, 4);
        int data;
        while ((data = byteStream.read()) != -1) {
            System.out.print((char) data);
        }
    }
}
