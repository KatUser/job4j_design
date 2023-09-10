package ru.job4j.generics;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GenericUsageBefore {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("first");
        list.add("second");
        list.add("third");
        list.add(new Person("Petro", 45, new Date(913913000L)));
        /* String o = list.get(1);
         компилятор не понимает, как мы можем Object привести к типу String
         */
        String s = (String) list.get(1);
        System.out.println("Size is " + list.size());
        for (int i = 0; i < list.size(); i++) {
            String line = (String) list.get(i);
            System.out.println("Current element is : " + line);
            /*Невозможно привести тип Person к типу String */
        }

    }
}
