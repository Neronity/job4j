package ru.job4j.map;

import java.util.*;

public class SimpleHashMap<K, V> implements Iterable<V> {

    /* ---------------- Node class -------------- */

    static class Node<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final String toString() {
            return key + "=" + value;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Node)) {
                return false;
            }
            Node<?, ?> node = (Node<?, ?>) o;
            return Objects.equals(key, node.key)
                    && Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }

    /* ---------------- Fields -------------- */

    private int modCount = 0;
    private Node<K, V>[] table;
    private int size = 0;
    private int length;
    private double loadFactor = 0.75D;
    private int threshold;


    /* ---------------- Static hash function -------------- */

    static private int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /* ---------------- Public methods -------------- */

    public boolean insert(K key, V value) {
        return putVal(hash(key), key, value);
    }

    public V get(K key) {
        Node<K, V> e;
        return (e = getNode(hash(key), key)) == null ? null : e.value;
    }

    public boolean delete(K key) {
        return removeNode(hash(key), key);
    }

    /* ---------------- Private methods -------------- */

    private final boolean putVal(int hash, K key, V value) {
        Node<K, V>[] tab;
        boolean result = false;
        int n, i;

        if ((tab = table) == null || (n = tab.length) == 0) {
            n = (tab = resize()).length;
        }
        if ((tab[i = (n - 1) & hash]) == null) {
            tab[i] = new Node<>(hash, key, value, null);
            result = true;
        }
        if (result) {
            ++modCount;
            if (++size > threshold) {
                resize();
            }
        }
        return result;
    }

    private Node<K, V>[] resize() {
        Node<K, V>[] result;
        if (table == null) {
            result = (Node<K, V>[]) new Node[2];
            length = result.length;
        } else {
            int newLength = table.length * 2;
            Node<K, V>[] newTab = (Node<K, V>[]) new Node[newLength];
            for (Node<K, V> currentNode : table) {
                if (currentNode != null) {
                    newTab[newLength - 1 & hash(currentNode.key)] = currentNode;
                }
            }
            result = newTab;
        }
        threshold = (int) (length * loadFactor);
        table = result;
        return result;
    }

    private Node<K, V> getNode(int hash, Object key) {
        Node<K, V>[] tab;
        Node<K, V> first, e;
        Node<K, V> result = null;
        int n;
        K k;
        if ((tab = table) != null && (n = tab.length) > 0
                && (first = tab[(n - 1) & hash]) != null) {
            if (first.hash == hash
                    && ((k = first.key) == key || (key != null && key.equals(k)))) {
                result = first;
            }
        }
        return result;
    }

    private boolean removeNode(int hash, K key) {
        Node<K, V>[] tab;
        boolean result = false;
        int n, i;
        if ((tab = table) != null && tab.length != 0) {
            if ((tab[i = (table.length - 1) & hash]) != null) {
                tab[i] = null;
                result = true;
            }
        }
        return result;
    }

    /* ---------------- Iterator -------------- */

    @Override
    public Iterator<V> iterator() {
        return new Itr();
    }

    class Itr implements Iterator<V> {
        Node<K, V> next;
        Node<K, V> current;
        int expectedModCount;
        int index;

        Itr() {
            expectedModCount = modCount;
            Node<K, V>[] t = table;
            current = next = null;
            index = 0;
            if (t != null && size > 0) {
                do {
                } while (index < t.length && (next = t[index++]) == null);
            }
        }

        public final boolean hasNext() {
            return next != null;
        }

        public V next() {
            return nextNode().value;
        }

        final Node<K, V> nextNode() {
            Node<K, V>[] t;
            Node<K, V> e = next;
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (e == null) {
                throw new NoSuchElementException();
            }
            if ((next = (current = e).next) == null && (t = table) != null) {
                do {
                } while (index < t.length && (next = t[index++]) == null);
            }
            return e;
        }
    }
}
