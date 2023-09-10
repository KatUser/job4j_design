package ru.job4j.generics;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GenericUsage {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("fisrt");
        list.add("second");
        list.add("thrid");
        /* list.add(new Person("name", 40, new Date(91391300000L))); */
        for (String line : list) {
            System.out.println(line);
        }
    }
}
