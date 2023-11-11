package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        boolean wroteTheStartOfDeadServer = false;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(source))) {
            for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                String[] words = line.split(" ");
                if ((("400").equals(words[0]) || ("500").equals(words[0])) && !wroteTheStartOfDeadServer) {
                    try (PrintWriter printWriter = new PrintWriter(new BufferedOutputStream(
                            new FileOutputStream(target, true)))) {
                        printWriter.print(words[1] + ";");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    wroteTheStartOfDeadServer = true;
                }
                if ((("200").equals(words[0]) || ("300").equals(words[0])) && wroteTheStartOfDeadServer) {
                    try (PrintWriter printWriter = new PrintWriter(new BufferedOutputStream(
                            new FileOutputStream(target, true)))) {
                        printWriter.println(words[1] + ";");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    wroteTheStartOfDeadServer = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
