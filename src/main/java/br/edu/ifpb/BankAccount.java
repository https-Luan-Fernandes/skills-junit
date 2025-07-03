package br.edu.ifpb;

import java.math.BigDecimal;

public class BankAccount {

    private BigDecimal balance;

    BankAccount(BigDecimal balance) {
        if (balance == null) {
            throw new IllegalArgumentException("Initial balance must be at least 100");

        }
        if(balance.compareTo(BigDecimal.valueOf(100)) < 0) {
            throw new IllegalArgumentException("Balance cannot less than 100");
        }
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void deposit(BigDecimal amount) {
        if (amount == null) {
            throw new NullPointerException("Deposit amount cannot be null");
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        this.balance = balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        if (amount == null) {
            throw new NullPointerException("Withdrawal amount cannot be null");
        }
        if(amount.compareTo(BigDecimal.ZERO) <= 0 || amount.compareTo(this.balance) > 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        this.balance = balance.subtract(amount);
    }
}
