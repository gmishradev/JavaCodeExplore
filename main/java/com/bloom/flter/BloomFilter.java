package com.bloom.flter;

import java.util.BitSet;

public class BloomFilter {
    private final int size;
    private final BitSet bitSet;
    private final int[] hashFunctions;

    public BloomFilter(int size, int numHashFunctions) {
        this.size = size;
        this.bitSet = new BitSet(size);
        this.hashFunctions = new int[numHashFunctions];
    }

    public void add(int value) {
        for (int i = 0; i < hashFunctions.length; i++) {
            int hash = hash(value, i);
            bitSet.set(hash);
        }
    }

    public boolean contains(int value) {
        for (int i = 0; i < hashFunctions.length; i++) {
            int hash = hash(value, i);
            if (!bitSet.get(hash)) {
                return false;
            }
        }
        return true;
    }

    private int hash(int value, int index) {
        // Implement hash functions here
        // Example: return (value * index) % size;
        return (value * index) % size;
    }
}
