package ru.job4j.map;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        int h = hash(Objects.hashCode(key));
        int i = indexFor(h);
        boolean result = table[i] == null;
        if (result) {
            table[i].key = key;
            table[i].value = value;
            count++;
            modCount++;
        }
        return result;
    }

    private int hash(int hashCode) {
        return hashCode % table.length;
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private void expand() {

    }

    @Override
    public V get(K key) {
        V result = null;
        int hk = Objects.hashCode(key);
        int i = indexFor(hash(hk));
        if (table[i] != null) {
            if (Objects.hashCode(key) == Objects.hashCode(table[i])
                    && Objects.equals(table[i].key, key)) {
                result = table[i].value;
            }
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int hk = Objects.hashCode(key);
        int i = indexFor(hash(hk));
        if (table[i] != null) {
            if (Objects.hashCode(key) == Objects.hashCode(table[i])
                    && Objects.equals(table[i].key, key)) {
                table[i] = null;
                result = true;
            }
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int iterIndex = 0;

            @Override
            public boolean hasNext() {
                while (iterIndex < table.length && table[iterIndex] == null) {
                    iterIndex++;
                }
                return iterIndex < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[iterIndex++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
