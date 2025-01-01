package com.generic;

/**
 * Override Methods: You can override methods for specific enum constants by defining abstract methods in the enum.
 */

public enum Operation {
    PLUS {
        public double apply(double x, double y) {
            return x + y;
        }

        @Override
        public void execute(Operation operation) {

        }
    },
    MINUS {
        public double apply(double x, double y) {
            return x - y;
        }

        @Override
        public void execute(Operation operation) {

        }
    },
    TIMES {
        public double apply(double x, double y) {
            return x * y;
        }

        @Override
        public void execute(Operation operation) {

        }
    },

    DIVIDE {
        public double apply(double x, double y) {
            return x / y;
        }

        @Override
        public void execute(Operation operation) {

        }
    };

    public abstract double apply(double x, double y);

    public static Operation getOperationByName(String name) {
        for (Operation operation : Operation.values()) {
            if (operation.name().equalsIgnoreCase(name)) {
                return operation;
            }
        }

        throw new IllegalArgumentException("Invalid operation name: " + name);
    }

    public abstract void execute(Operation operation);

    public static void main(String[] args) {
        Operation operation = Operation.TIMES;
        System.out.println(operation.apply(5, 3)); // Output: 15

        Operation operation2 = Operation.PLUS;

        System.out.println(operation2.apply(2, 3));
    }
}

