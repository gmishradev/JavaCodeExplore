package com;

// Custom exception for insufficient funds
public class InsufficientFundsException extends Exception {
    private final double amountRequested;

    // Constructor
    public InsufficientFundsException(double amountRequested, double currentBalance) {
        super(String.format("Insufficient funds: Requested %.2f, but current balance is %.2f", amountRequested, currentBalance));
        this.amountRequested = amountRequested;
    }

    public double getAmountRequested() {
        return amountRequested;
    }
}

// Custom exception for invalid account number
 class InvalidAccountNumberException extends Exception {
    private final String accountNumber;

    // Constructor
    public InvalidAccountNumberException(String accountNumber) {
        super(String.format("Invalid account number: %s", accountNumber));
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}

// Custom exception for unauthorized access
 class UnauthorizedAccessException extends Exception {
    // Constructor
    public UnauthorizedAccessException(String message) {
        super(message);
    }
}

// BankAccount class
 class BankAccount {
    private String accountNumber;
    private double balance;

    // Constructor
    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    // Method to withdraw funds
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException(amount, balance);
        }
        balance -= amount;
    }

    // Method to deposit funds
    public void deposit(double amount) {
        balance += amount;
    }

    // Method to transfer funds
    public void transfer(double amount, BankAccount targetAccount)
            throws InsufficientFundsException, UnauthorizedAccessException {
        if (amount > balance) {
            throw new InsufficientFundsException(amount, balance);
        }
        if (targetAccount == null) {
            throw new UnauthorizedAccessException("Target account is null.");
        }
        balance -= amount;
        targetAccount.deposit(amount);
    }
    public static void main(String[] args) {
        BankAccount account1 = new BankAccount("12345", 500.00);
        BankAccount account2 = new BankAccount("67890", 300.00);

        try {
            account1.withdraw(600.00); // This will throw InsufficientFundsException
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage()); // Outputs: Insufficient funds: Requested 600.00, but current balance is 500.00
        }

        try {
            account1.transfer(100.00, null); // This will throw UnauthorizedAccessException
        } catch (UnauthorizedAccessException | InsufficientFundsException e) {
            e.printStackTrace();
            System.out.println(e.getMessage()); // Outputs: Target account is null.
        }

        try {
            account1.transfer(100.00, account2); // Successful transfer
            System.out.println("Transfer successful");
            System.out.println("Account1 balance: " + account1.getBalance()); // Should reflect deduction
            System.out.println("Account2 balance: " + account2.getBalance()); // Should reflect addition
        } catch (InsufficientFundsException | UnauthorizedAccessException e) {
            e.printStackTrace();
            System.out.println();
            System.out.println(e.getMessage());
        }
    }
}
