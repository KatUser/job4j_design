package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleArraySet<T> implements SimpleSet<T> {
    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        boolean addedValue = !contains(value);
        if (addedValue) {
            set.add(value);
        }
        return addedValue;
    }

    @Override
    public boolean contains(T value) {
        boolean hasValue = false;
        for (T t : set) {
            if (Objects.equals(t, value)) {
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
