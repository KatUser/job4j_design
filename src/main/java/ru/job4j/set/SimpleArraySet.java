package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;

public class SimpleArraySet<T> implements SimpleSet<T> {
    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        boolean canAddValue = false;
        if (contains(value)) {
            canAddValue = false;
        } else {
            set.add(value);
            canAddValue = true;
        }
        return canAddValue;
    }

    @Override
    public boolean contains(T value) {
        boolean hasValue = false;
        for (int i = 0; i < set.size(); i++) {
            if (set.get(i).equals(value)) {
                hasValue = true;
                break;
            }
        }
        return hasValue;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
