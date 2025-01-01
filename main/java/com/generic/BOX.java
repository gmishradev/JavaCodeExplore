package com.generic;

public class BOX <T>{

   private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }


    public static void main(String[] args) {
        BOX<Integer> box1 = new BOX<>();
        box1.setValue(10);
        System.out.println(box1.getValue());

        BOX<String> box2 = new BOX<>();
        box2.setValue("Hello");
        System.out.println(box2.getValue());

        Pair<Integer, String> pair = new Pair<>(box1.getValue(), box2.getValue());
        System.out.println(pair.getKey() + " " + pair.getValue());
    }
}
