package com.code.hashmap;

/**
 * 1. Explain the Internal Structure of a HashMap.
 * Answer: A HashMap uses an array of buckets (or "bins"). Each bucket is a linked list or a Red-Black Tree (introduced in Java 8)
 * when collisions occur. The hash code of the key determines the bucket index. The internal structure includes nodes (entries)
 * that store the key, value, and a reference to the next entry (or a tree node).
 *
 * 2. Explain the Importance of hashCode and equals Methods in HashMap.
 * Answer: The hashCode method determines the bucket index for a key. The equals method is used to compare keys within a
 * bucket to find the exact entry. It is crucial that hashCode and equals are consistent; if two keys are equal according to equals,
 * they must have the same hash code. Otherwise, the HashMap may not function correctly
 *
 * @param <K>
 * @param <V>
 */
public class MyHashMap<K, V> {
    private static final int INITIAL_CAPACITY = 16; // Initial capacity of the hash table
    private static final float LOAD_FACTOR = 0.75f; // Load factor for resizing

    private Entry<K, V>[] table;
    private int size = 0;

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

    // Constructor
    @SuppressWarnings("unchecked")
    public MyHashMap() {
        table = (Entry<K, V>[]) new Entry[INITIAL_CAPACITY];
    }

    // Hash function
    private int hash(K key) {
        return key == null ? 0 : key.hashCode() & (table.length - 1);
    }

    // Put method
    public void put(K key, V value) {
        int index = hash(key);
        Entry<K, V> current = table[index];

        // Check if the key already exists
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        // Add new entry
        table[index] = new Entry<>(key, value, table[index]);
        size++;

        // Check if resizing is needed
        if (size > table.length * LOAD_FACTOR) {
            resize();
        }
    }

    // Get method
    public V get(K key) {
        int index = hash(key);
        Entry<K, V> current = table[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null; // Key not found
    }

    // Remove method
    public V remove(K key) {
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
        return null; // Key not found
    }

    // Resize method
    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = table.length * 2;
        Entry<K, V>[] newTable = (Entry<K, V>[]) new Entry[newCapacity];

        // Rehash all entries
        for (Entry<K, V> entry : table) {
            while (entry != null) {
                Entry<K, V> next = entry.next;
                int newIndex = entry.key == null ? 0 : entry.key.hashCode() & (newCapacity - 1);
                entry.next = newTable[newIndex];
                newTable[newIndex] = entry;
                entry = next;
            }
        }

        table = newTable;
    }

    // Size method
    public int size() {
        return size;
    }

    // Main method to test the custom hash map
    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();
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
