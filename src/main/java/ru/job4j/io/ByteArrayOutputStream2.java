package ru.job4j.io;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteArrayOutputStream2 {
    public static void main(String[] args) {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] bytes = "Message".getBytes();
        outStream.writeBytes(bytes);
        try (FileOutputStream fileOutput = new FileOutputStream("data/message.txt")) {
            outStream.writeTo(fileOutput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
