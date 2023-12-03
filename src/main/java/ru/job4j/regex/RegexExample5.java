package ru.job4j.regex;

import java.util.Arrays;

public class RegexExample5 {
    public static void main(String[] args) {
        String str = "123+=-456:/789";
        String[] rsl = str.split("\\D+");
        System.out.println(Arrays.toString(rsl));

    }
}
