package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class PrintWriterAndStreamUsage {
    public static void main(String[] args) {
        try (PrintStream stream = new PrintStream(new FileOutputStream("data/print3.txt"));
             PrintWriter writer = new PrintWriter("data/write.txt")) {
            stream.println("From PrintStream into FileOutputStream");
            stream.write("New line".getBytes());
            writer.println("New Message by writer");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
