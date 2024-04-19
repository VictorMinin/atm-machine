package com.solvd.atm.models;

public class TransactionStatus {

    private int transactionStatusId;
    private String statusName;

    public TransactionStatus() {
    }

    public TransactionStatus(String statusName) {
        this.statusName = statusName;
    }

    public int getTransactionStatusId() {
        return transactionStatusId;
    }

    public void setTransactionStatusId(int transactionStatusId) {
        this.transactionStatusId = transactionStatusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return statusName;
    }
}
