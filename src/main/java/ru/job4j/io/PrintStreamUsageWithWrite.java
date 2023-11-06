package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class PrintStreamUsageWithWrite {
    public static void main(String[] args) {
        try (PrintStream stream = new PrintStream(new FileOutputStream("data/print2.txt"))) {
            stream.println("From PrintStream into FileoutputStream");
            stream.write("New line".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
