package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class ResultFileForMultiply {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("data/multiplyresultfile.txt")) {
            for (int i = 0; i <= 10; i++) {
                out.write("1 * ".getBytes());
                out.write(String.valueOf(i).getBytes());
                out.write(" = ".getBytes());
                out.write(String.valueOf(i * 1).getBytes());
                out.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}