package ru.job4j.generics;

import java.util.*;

public class WildCardUse {
    public void printRsl(Collection<?> col) {
        for (Iterator<?> iterator = col.iterator(); iterator.hasNext();) {
            Object next = iterator.next();
            System.out.println(next);
        }
    }

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        new WildCardUse().printRsl(list);

        List<String> strings = List.of("cat", "dog", "owl");
        new WildCardUse().printRsl(strings);

        List<Person> persons = List.of(
                new Person("Alla", 23, new Date(123214444L)),
                new Person("Igor", 48, new Date(564545454L))
                );
        new WildCardUse().printRsl(persons);
    }
}
