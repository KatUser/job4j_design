package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {
    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (container.length == size) {
            expand();
        }
        container[size++] = value;
        modCount++;
    }

    @Override /* Replaces the element at the specified position in this list with the specified element.*/
    public T set(int index, T newValue) {
        T previousIndexValue = get(index);
        container[index] = newValue;
        return previousIndexValue;
    }

    @Override /* Returns: the element that was removed from the list */
    public T remove(int index) {
        T previousIndexValue = get(index);
        System.arraycopy(
                container, index + 1, container,
                index, container.length - index - 1
        );
        container[container.length - 1] = null;
        modCount++;
        size--;
        return previousIndexValue;
    }

    @Override /* Returns the element at the specified position in this list. */
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override /* Returns the number of elements in this list.*/
    public int size() {
        return size;
    }

    private void expand() {
        if (container.length == 0) {
            container = Arrays.copyOf(container, 1);
        } else {
            container = Arrays.copyOf(container, container.length * 2);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int i = 0;
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return size != i;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[i++];
            }
        };
    }
}
