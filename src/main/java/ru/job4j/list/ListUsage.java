package ru.job4j.list;

import java.util.*;

public class ListUsage {
    public static void main(String[] args) {
        List<String> rsl = new ArrayList<>();
        rsl.add("one");
        rsl.add("two");
        rsl.add("three");
//        rsl.forEach(System.out::println);
//        System.out.println();
//        rsl.add(1, "four");
//        rsl.forEach(System.out::println);
        List<String> list = new ArrayList<>();
        list.add("two");
        list.add("six");
//        rsl.addAll(list);
//        System.out.println();
//        rsl.forEach(System.out::println);
//        List<String> list2 = new ArrayList<>();
//        list2.add("seven");
//        list2.add("eight");
//        rsl.addAll(0, list2);
//        System.out.println();
//        rsl.forEach(System.out::println);
//        System.out.println();
//        for (int i = 0; i < rsl.size(); i++) {
//            System.out.println("Current element is : " + rsl.get(i));
//        }
//        System.out.println("When using iterator");
//        Iterator<String> iterator = rsl.iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }
//        System.out.println("When using listiterator");
//        ListIterator<String> iterator1 = rsl.listIterator();
//        while (iterator1.hasNext()) {
//            System.out.println(iterator1.next());
//        }
//        System.out.println();
//        ListIterator<String> iterator2 = rsl.listIterator(5);
//        while (iterator2.hasNext()) {
//            System.out.println(iterator2.next());
//        }
//        System.out.println();
//        rsl.set(0, "twenty");
//        rsl.forEach(System.out::println);
//        rsl.replaceAll(String::toUpperCase);
//        rsl.forEach(System.out::println);
        System.out.println(rsl.remove(0));
        rsl.forEach(System.out::println);
//        rsl.removeAll(list);

        System.out.println();
        rsl.addAll(list);
        rsl.forEach(System.out::println);
        System.out.println();
        rsl.removeIf(s -> s.length() > 4);
        rsl.forEach(System.out::println);
        System.out.println(rsl.contains("eleven"));
        System.out.println(rsl.indexOf("cat"));
        System.out.println(rsl.indexOf("six"));
        rsl.add("two");
        System.out.println(rsl.lastIndexOf("two"));
        List<String> list2 = rsl.subList(1, 2);
        for (String s : list2) {
            System.out.println("Текущий элемент: " + s);
        }
        rsl.sort(Comparator.reverseOrder());
        rsl.forEach(System.out::println);
        System.out.println(5 % 16);
        System.out.println(31 % 15);



    }
}
