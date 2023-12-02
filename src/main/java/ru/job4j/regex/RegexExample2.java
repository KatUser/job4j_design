package ru.job4j.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample2 {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("Jobj4");
        String text = "Jobj4 and Jobj4 and Jobj4";
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println("Matching found : " + matcher.group());
        }
    }
}
