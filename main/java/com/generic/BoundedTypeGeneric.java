package com.generic;

public class BoundedTypeGeneric<T extends  Number> {
    T value;

    public BoundedTypeGeneric(T value) {
        this.value = value;
    }

    public static void main(String[] args) {
        BoundedTypeGeneric<Integer> integerBoundedTypeGeneric = new BoundedTypeGeneric<>(10);
        System.out.println(integerBoundedTypeGeneric.value);

        BoundedTypeGeneric<Double> doubleBoundedTypeGeneric = new BoundedTypeGeneric<>(10.5);
        System.out.println(doubleBoundedTypeGeneric.value);

        // Error: BoundedTypeGeneric<String> stringBoundedTypeGeneric = new BoundedTypeGeneric<>("Hello");
    }
}
