package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean result = false;
        if (modCount >= capacity * LOAD_FACTOR) {
            expand();
        }
        int i = indexFor(key);
        if (table[i] == null) {
            table[i] = new MapEntry<>(key, value);
            modCount++;
            result = true;
        }
        return result;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(K key) {
        return (table.length - 1) & hash(Objects.hashCode(key));
    }

    private void expand() {
        MapEntry<K, V>[] oldTable = Arrays.copyOf(table, capacity);
        int newCap = capacity << 1;
        capacity = newCap;
        table = new MapEntry[newCap];
        for (MapEntry<K, V> entry: oldTable) {
            if (entry != null) {
                table[indexFor(entry.key)] = entry;
            }
        }
    }

    private boolean checkKeyEquals(K key, int i) {
        return table[i] != null
                && Objects.hashCode(key) == Objects.hashCode(table[i].key)
                && Objects.equals(key, table[i].key);
    }


    @Override
    public V get(K key) {
        int i = indexFor(key);
        boolean keyEquals = checkKeyEquals(key, i);
        return keyEquals ? table[i].value : null;
    }

    @Override
    public boolean remove(K key) {
        int i = indexFor(key);
        boolean keyEquals = checkKeyEquals(key, i);
        if (keyEquals) {
            table[i] = null;
            modCount--;
        }
        return keyEquals;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int cursor;
            int expectedModCount = modCount;
            @Override
            public boolean hasNext() throws ConcurrentModificationException {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (cursor < table.length && table[cursor] == null) {
                    cursor++;
                }
                return cursor < table.length;
            }
            @Override
            public K next() throws NoSuchElementException {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[cursor++].key;
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