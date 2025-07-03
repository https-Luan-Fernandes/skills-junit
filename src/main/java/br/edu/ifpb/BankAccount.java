package br.edu.ifpb;

public class BankAccount {

    private double balance;

    BankAccount(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return this.balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        this.balance += amount;
    }

    public void withdraw(double amount) {
        if(amount <= 0 || amount > this.balance) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        this.balance -= amount;
    }
}
