package com.generic;

import java.util.List;

public class WildCardGeneric<T, P, S> {
    private final T value;
    private final P p;
    private final S parent;
// ? super - lower bound -> all the paren is allowed
    // extend  - upper bound  (parent class) - all the children of the parent class is allowed


    public WildCardGeneric(T value, P p, S parent) {
        this.value = value;
        this.p = p;
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "WildCardGenric{" +
                "value=" + value +
                ", p=" + p +
                ", parent=" + parent +
                '}';
    }

    public static void main(String[] args) {
        WildCardGeneric<Double, String, Object> wildCardGeneric = new WildCardGeneric<>(10.0, "Hello", "World");
        System.out.println(wildCardGeneric);

    }


    public void printArray(List<?> list) {
        for (Object obj : list) {
            System.out.println(obj);
        }
    }

}
