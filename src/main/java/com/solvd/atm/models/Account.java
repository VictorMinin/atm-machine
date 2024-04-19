package com.solvd.atm.models;

public class Account {

    private int accountId;
    private double amount;
    private User user;
    private double creditMax;

    public Account() {
    }

    public Account(double amount, User user) {
        this.amount = amount;
        this.user = user;
    }

    public Account(double amount, User user, double creditMax) {
        this.amount = amount;
        this.user = user;
        this.creditMax = creditMax;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getCreditMax() {
        return creditMax;
    }

    public void setCreditMax(double creditMax) {
        this.creditMax = creditMax;
    }

    @Override
    public String toString() {
        return "ID: " + accountId +
                "\nAmount: " + amount +
                "\nUser: " + user +
                (creditMax != 0.0 ? "\nMax Credit: " + creditMax : "");
    }
}
