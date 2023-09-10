package ru.job4j.generics;

import java.util.Date;

public class GenericsClass<K, V> {
    private K key;

    private V value;

    public GenericsClass(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "GenericsClass{"
                + "key=" + key
                + ", value=" + value
                + '}';
    }

    public static void main(String[] args) {
        GenericsClass<String, String> first =
                new GenericsClass<>("First key", "Second key");
        System.out.println(first);

        GenericsClass<Person, Person> second =
                new GenericsClass<>(new Person("Vasya", 45, new Date(137925555L)),
                        new Person("Olya", 34, new Date(1249999L)));
        System.out.println(second);
    }
}