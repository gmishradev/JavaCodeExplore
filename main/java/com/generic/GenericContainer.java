package com.generic;

public class GenericContainer <T> implements Container<T> {

    T value;

    public GenericContainer(T value) {
        this.value = value;
    }
    @Override
    public void add(T item) {
        this.value = item;

    }

    @Override
    public T get() {
        return value;
    }
}
