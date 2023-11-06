package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class PrintStreamUsage {
    public static void main(String[] args) {
        try (PrintStream stream = new PrintStream(new FileOutputStream("data/input.txt"))) {
            stream.println("From PrintStream into FileOutputStream");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

