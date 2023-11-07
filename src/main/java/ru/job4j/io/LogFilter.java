package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> logList = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader("data/log.txt"))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] words = line.split(" ");
                if (("404").equals(words[words.length - 2])) {
                    logList.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return logList;
    }

    public void saveTo(String out) {
        var data = filter();
        try (PrintWriter res = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream("data/out.txt")
                )
        )) {
            for (String str : data) {
                res.println(str);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);
        logFilter.saveTo("data/log.txt");
    }
}