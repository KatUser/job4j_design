package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleArraySet<T> implements SimpleSet<T> {
    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        boolean addedValue = false;
        if (!contains(value)) {
            set.add(value);
            addedValue = true;
        }
        return addedValue;
    }

    @Override
    public boolean contains(T value) {
        boolean hasValue = false;
        for (int i = 0; i < set.size(); i++) {
            if (Objects.equals(set.get(i), value)) {
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
