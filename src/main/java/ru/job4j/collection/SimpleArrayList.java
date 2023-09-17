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
            container = expand(container);
            container[size + 1] = value;
        }
        container[size] = value;
        size++;
        modCount++;
    }

    @Override /* Replaces the element at the specified position in this list with the specified element.*/
    public T set(int index, T newValue) {
        T previousIndexValue = container[index];
        if (Objects.checkIndex(index, size) == index) {
            container[index] = newValue;
        } else {
            throw new IndexOutOfBoundsException();
        }
        return previousIndexValue;
    }

    @Override /* Returns: the element that was removed from the list */
    public T remove(int index) {
        if (Objects.checkIndex(index, size) == index) {
            T previousIndexValue = container[index];
            System.arraycopy(
                    container,
                    index + 1,
                    container,
                    index,
                    container.length - index - 1
            );
            container[container.length - 1] = null;
            modCount++;
            size--;
            return previousIndexValue;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override /* Returns the element at the specified position in this list. */
    public T get(int index) {
        if (Objects.checkIndex(index, size) == index) {
            return container[index];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override /* Returns the number of elements in this list.*/
    public int size() {
        return size;
    }

    public T[] expand(T[] container) {
        if (container.length == 0) {
            container = Arrays.copyOf(container, 1);
        }
        container = Arrays.copyOf(container, container.length * 2);
        return container;
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
