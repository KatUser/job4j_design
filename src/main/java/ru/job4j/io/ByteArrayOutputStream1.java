package ru.job4j.io;

import java.io.ByteArrayOutputStream;

public class ByteArrayOutputStream1 {
    public static void main(String[] args) {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] bytes = "Message".getBytes();
        outStream.writeBytes(bytes);
        System.out.println(outStream);
    }
}
