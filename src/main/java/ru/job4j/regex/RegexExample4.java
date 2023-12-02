package ru.job4j.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample4 {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("123");
        String text = "1231 and 1232 and 1233";
        Matcher matcher = pattern.matcher(text);
        String rsl = matcher.replaceAll("job4j");
        System.out.println(rsl);
        Pattern pattern1 = Pattern.compile("and");
        Matcher matcher1 = pattern1.matcher(rsl);
        String rsl2 = matcher1.replaceAll("&");
        System.out.println(rsl2);
    }
}
