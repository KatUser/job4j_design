package ru.job4j.generics;

import java.util.Date;

public class Person {
    private String name;
    private int age;
    private Date birthdate;

    public Person(String name, int age, Date birthdate) {
        this.name = name;
        this.age = age;
        this.birthdate = birthdate;
    }
}
