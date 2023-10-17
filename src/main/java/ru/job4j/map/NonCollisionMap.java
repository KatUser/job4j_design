package ru.job4j.map;

import java.util.ConcurrentModificationException;
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
        if (count  >= capacity * LOAD_FACTOR) {
            expand();
        }
        int h = hash(Objects.hashCode(key));
        int i = indexFor(h);
        boolean result = table[i] == null;
        if (result) {
            table[i] = new MapEntry<>(key, value);
            count++;
            modCount++;
        }
        return result;
    }

    private int hash(int hashCode) {
        return hashCode == 0 ? 0 : hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private void expand() {
        MapEntry<K, V>[] newTable = new MapEntry[capacity * 2];
        for (MapEntry<K, V> entry : table) {
            if (entry != null) {
                int h = hash(Objects.hashCode(entry.key));
                int newTableIndex = indexFor(h);
                newTable[newTableIndex] = new MapEntry<>(entry.key, entry.value);
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        V result = null;
        int hk = Objects.hashCode(key);
        int i = indexFor(hash(hk));
        if (table[i] != null) {
            if (Objects.equals(hk, Objects.hashCode(table[i].key))
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
            if (Objects.equals(hk, Objects.hashCode(table[i].key))
                    && Objects.equals(table[i].key, key)) {
                table[i] = null;
                result = true;
            }
        }
        count--;
        modCount++;
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int iterIndex = 0;
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
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
