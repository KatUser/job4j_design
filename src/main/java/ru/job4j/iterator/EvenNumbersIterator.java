package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;

    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean b = false;
        while (index < data.length) {
            if (data[index] % 2 == 0) {
                b = true;
                break;
            }
            index++;
        }
        return b;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

     @Override
     public void forEachRemaining(Consumer<? super Integer> action) {
         throw new UnsupportedOperationException();
     }
}
