package com.generic;

public class ConstructorGeneric {

    public <T extends Number> ConstructorGeneric(T t) {
        System.out.println("Constructor with generic parameter: " + t);
    }


    public static void main(String[] args) {
        ConstructorGeneric integerConstructorGeneric = new ConstructorGeneric(10);
        ConstructorGeneric doubleConstructorGeneric = new ConstructorGeneric(10.5);

        double result1 = Operation1.ADD.apply(10, 20);
        double result2 = Operation1.MULTIPLY.apply(5.5, 4);
        System.out.println(result1);  // Output: 30.0
        System.out.println(result2);  // Output: 22.0
    }

    enum Operation1 {
        ADD {
            public <T extends Number> double apply(T a, T b) {
                return a.doubleValue() + b.doubleValue();
            }

        }, SUBTRACT {
            public <T extends Number> double apply(T a, T b) {
                return a.doubleValue() - b.doubleValue();
            }
        }, MULTIPLY {
            public <T extends Number> double apply(T a, T b) {
                return a.doubleValue() * b.doubleValue();
            }
        }, DIVIDE {
            @Override
            public <T extends Number> double apply(T a, T b) {
                return 0;
            }
        };

        public abstract <T extends Number> double apply(T a, T b);
    }

}
