package com.generic;

public enum PaymentMethod {
    CREDIT_CARD {
        @Override
        public double calculateFee(double amount) {
            return amount * 0.02; // 2% fee for credit card
        }

        @Override
        public void processTransaction(double amount) {
            System.out.println("Processing credit card transaction of $" + amount);
        }
    },
    PAYPAL {
        @Override
        public double calculateFee(double amount) {
            return amount * 0.03; // 3% fee for PayPal
        }

        @Override
        public void processTransaction(double amount) {
            System.out.println("Processing PayPal transaction of $" + amount);
        }
    },
    BITCOIN {
        @Override
        public double calculateFee(double amount) {
            return amount * 0.01; // 1% fee for Bitcoin
        }

        @Override
        public void processTransaction(double amount) {
            System.out.println("Processing Bitcoin transaction of $" + amount);
        }
    };

    // Abstract methods to be implemented by each enum constant
    public abstract double calculateFee(double amount);

    public abstract void processTransaction(double amount);

    public static void main(String[] args) {
        PaymentMethod[] paymentMethods = PaymentMethod.values();
        for (PaymentMethod paymentMethod : paymentMethods) {
            double amount = 100.0;
            double fee = paymentMethod.calculateFee(amount);
            System.out.println("Fee for " + paymentMethod + ": $" + fee);
            paymentMethod.processTransaction(amount);
        }

        PaymentService paymentService = new PaymentService();

        // Test Credit Card payment
        paymentService.processPayment(PaymentMethod.CREDIT_CARD, 100.00);

        // Test PayPal payment
        paymentService.processPayment(PaymentMethod.PAYPAL, 200.00);

        // Test Bitcoin payment
        paymentService.processPayment(PaymentMethod.BITCOIN, 300.00);
    }

    static class PaymentService {

        public void processPayment(PaymentMethod method, double amount) {
            double fee = method.calculateFee(amount);
            double totalAmount = amount + fee;
            System.out.println("Total amount to be charged: $" + totalAmount);
            method.processTransaction(totalAmount);
        }
    }

}
