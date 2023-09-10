package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;

public class LowerBoundedCardUsage {
    public void addAll(List<? super Integer> list) {
        for (int i = 0; i <= 5; i++) {
            list.add(i);
        }
        for (Object line : list) {
            System.out.println("Current element : " + line);
        }
    }

    public static void main(String[] args) {
        List<?super Integer> list = new ArrayList<>();
        new LowerBoundedCardUsage().addAll(list);
    }
}
