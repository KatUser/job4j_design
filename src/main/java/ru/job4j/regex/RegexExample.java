package ru.job4j.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("I am a job4j student", Pattern.CASE_INSENSITIVE);

        String text1 = "I am a job4j student";
        Matcher matcher1 = pattern.matcher(text1);
        boolean isPresent1 = matcher1.matches();
        System.out.println(isPresent1);

        String text2 = "I am a Job4j student";
        Matcher matcher2 = pattern.matcher(text2);
        boolean isPresent2 = matcher2.matches();
        System.out.println(isPresent2);

        String text3 = "I AM A job4j STUDENT";
        Matcher matcher3 = pattern.matcher(text3);
        boolean isPresent3 = matcher3.matches();
        System.out.println(isPresent3);

        Pattern pattern4 = Pattern.compile("student");
        String text4 = "I am a job4j student";
        Matcher matcher4 = pattern4.matcher(text4);
        boolean isPresent4 = matcher4.find();
        System.out.println(isPresent4);

        Pattern pattern2 = Pattern.compile("job4j");
        String text = "I am a job4j student";
        Matcher matcher = pattern2.matcher(text);
        boolean isPresent = matcher.find();
        System.out.println(isPresent);

    }
}
