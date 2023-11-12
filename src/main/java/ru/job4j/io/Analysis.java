package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        boolean serverIsDead = false;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(source));
             PrintWriter printWriter =
                     new PrintWriter(new BufferedOutputStream(new FileOutputStream(target, true)))) {
            for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                if ((line.contains("400") || line.contains("500")) && !serverIsDead) {
                    String[] lineSplit = line.split(" ");
                    printWriter.print(lineSplit[1] + ";");
                    serverIsDead = true;
                }
                if ((line.contains("200") || line.contains("300")) && serverIsDead) {
                    String[] lineSplit = line.split(" ");
                    printWriter.println(lineSplit[1] + ";");
                    serverIsDead = false;
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
