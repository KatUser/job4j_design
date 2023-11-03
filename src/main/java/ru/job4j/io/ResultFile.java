package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("data/dataresult.txt")) {
            out.write("Hello, Java!".getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write("Hello, Python!".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
