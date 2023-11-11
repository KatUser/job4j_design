package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        boolean wroteTheStartOfDeadServer = false;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(source))) {
            for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                if ((line.contains("400") || line.contains("500")) && !wroteTheStartOfDeadServer) {
                    writeTime(target, line);
                    wroteTheStartOfDeadServer = true;
                }
                if ((line.contains("200") || line.contains("300")) && wroteTheStartOfDeadServer) {
                    writeTime(target, line);
                    wroteTheStartOfDeadServer = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeTime(String target, String line) {
        String[] lineSplit = line.split(" ");
        try (PrintWriter printWriter = new PrintWriter(new BufferedOutputStream(
                new FileOutputStream(target, true)))) {
            if (("400").equals(lineSplit[0]) || ("500").equals(lineSplit[0])) {
                printWriter.print(lineSplit[1] + ";");
            } else {
                printWriter.println(lineSplit[1] + ";");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
