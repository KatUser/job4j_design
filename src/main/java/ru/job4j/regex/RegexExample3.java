package ru.job4j.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample3 {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("Job4j");
        String text = "Job4j1 and Job4j2 and Job4j3";
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println("Matching found. Starts at : " + matcher.start()
                + ", ends at : " + matcher.end());
        }

    }
}
