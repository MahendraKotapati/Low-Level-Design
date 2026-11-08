package ATM.entities;

import java.util.HashMap;
import java.util.Map;

public class BankAccount {
    private String accountNumber;
    private double balance;
    private Map<String, Card> cards;


    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.cards = new HashMap<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Map<String, Card> getCards() {
        return cards;
    }

    public synchronized void deposit(int depositAmount) {
        this.balance = this.balance + depositAmount;
    }

    public synchronized boolean withdraw(int withdrawAmount) {
        if (this.balance >= withdrawAmount) {
            this.balance = this.balance - withdrawAmount;
            return true;
        }
        return false;
    }
}

