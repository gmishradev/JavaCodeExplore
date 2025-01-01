package com.code.hashmap;


/**
 * Fail-Safe Iterators
 * Fail-safe iterators do not throw exceptions when the collection is modified during iteration.
 * Instead, they work on a clone of the collection, ensuring that modifications do not affect the iteration process.
 * 
 * @param <K>
 * @param <V>
 */
public class ConcurrentHashMap<K, V> {
    private static final int INITIAL_CAPACITY = 16; // Initial capacity of the hash table
    private static final float LOAD_FACTOR = 0.75f; // Load factor for resizing
    private static final int NUM_SEGMENTS = 16; // Number of segments for concurrency

    private final Segment<K, V>[] segments;

    // Entry class representing key-value pairs
    private static class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next; // Reference to the next entry in case of a collision

        Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    // Segment class responsible for a portion of the hash map
    private static class Segment<K, V> {
        private Entry<K, V>[] table;
        private final Object lock = new Object();
        private int size = 0;

        @SuppressWarnings("unchecked")
        Segment(int capacity) {
            table = (Entry<K, V>[]) new Entry[capacity];
        }

        private int hash(K key) {
            return key == null ? 0 : (key.hashCode() & 0x7FFFFFFF) % table.length;
        }

        void put(K key, V value) {
            synchronized (lock) {
                int index = hash(key);
                Entry<K, V> current = table[index];

                while (current != null) {
                    if (current.key.equals(key)) {
                        current.value = value;
                        return;
                    }
                    current = current.next;
                }

                table[index] = new Entry<>(key, value, table[index]);
                size++;

                if (size > table.length * LOAD_FACTOR) {
                    resize();
                }
            }
        }

        V get(K key) {
            synchronized (lock) {
                int index = hash(key);
                Entry<K, V> current = table[index];

                while (current != null) {
                    if (current.key.equals(key)) {
                        return current.value;
                    }
                    current = current.next;
                }
                return null;
            }
        }

        V remove(K key) {
            synchronized (lock) {
                int index = hash(key);
                Entry<K, V> current = table[index];
                Entry<K, V> previous = null;

                while (current != null) {
                    if (current.key.equals(key)) {
                        if (previous == null) {
                            table[index] = current.next;
                        } else {
                            previous.next = current.next;
                        }
                        size--;
                        return current.value;
                    }
                    previous = current;
                    current = current.next;
                }
                return null;
            }
        }

        @SuppressWarnings("unchecked")
        private void resize() {
            int newCapacity = table.length * 2;
            Entry<K, V>[] newTable = (Entry<K, V>[]) new Entry[newCapacity];

            for (Entry<K, V> entry : table) {
                while (entry != null) {
                    Entry<K, V> next = entry.next;
                    int newIndex = entry.key == null ? 0 : (entry.key.hashCode() & 0x7FFFFFFF) % newCapacity;
                    entry.next = newTable[newIndex];
                    newTable[newIndex] = entry;
                    entry = next;
                }
            }

            table = newTable;
        }
    }

    // Constructor
    @SuppressWarnings("unchecked")
    public ConcurrentHashMap() {
        segments = (Segment<K, V>[]) new Segment[NUM_SEGMENTS];
        for (int i = 0; i < NUM_SEGMENTS; i++) {
            segments[i] = new Segment<>(INITIAL_CAPACITY);
        }
    }

    private Segment<K, V> getSegment(K key) {
        int segmentIndex = (key == null ? 0 : key.hashCode()) % NUM_SEGMENTS;
        return segments[segmentIndex];
    }

    public void put(K key, V value) {
        getSegment(key).put(key, value);
    }

    public V get(K key) {
        return getSegment(key).get(key);
    }

    public V remove(K key) {
        return getSegment(key).remove(key);
    }

    public int size() {
        int totalSize = 0;
        for (Segment<K, V> segment : segments) {
            synchronized (segment.lock) {
                totalSize += segment.size;
            }
        }
        return totalSize;
    }

    // Main method to test the custom concurrent hash map
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);

        System.out.println("Size: " + map.size()); // Output: Size: 3
        System.out.println("Value for 'Two': " + map.get("Two")); // Output: Value for 'Two': 2

        map.remove("Two");
        System.out.println("Size after removal: " + map.size()); // Output: Size after removal: 2
        System.out.println("Value for 'Two' after removal: " + map.get("Two")); // Output: Value for 'Two' after removal: null
    }
}
