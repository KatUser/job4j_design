package ru.job4j.generics;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class BoundedWildCardUsage {
    public void printInfo(Collection<? extends Person> personCollection) {
        for (Iterator<? extends Person> iterator = personCollection.iterator();
        iterator.hasNext();) {
            Person next = iterator.next();
            System.out.println(next);
            /* Тип и все его подтипы мб использованы */
        }
    }

    public static void main(String[] args) {
        List<Person> per = List.of(new Person("name", 21, new Date(913716000000L)));
        new BoundedWildCardUsage().printInfo(per);

        List<Programmer> pro = List.of(new Programmer("name123", 23, new Date(913716000000L)));
        new BoundedWildCardUsage().printInfo(pro);
    }
}
