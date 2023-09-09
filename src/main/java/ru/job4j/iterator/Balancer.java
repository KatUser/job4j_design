package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        /* В итераторе source передют последовательность, nodes - списки для распределения */
        for (int i = 0; i <= nodes.size(); i++) {
            if (i == nodes.size()) {
                i = 0;
            }
            if (!source.hasNext()) {
                break;
            }
            nodes.get(i).add(source.next());
        }
    }
}
