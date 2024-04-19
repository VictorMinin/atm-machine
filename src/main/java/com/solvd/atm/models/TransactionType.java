package com.solvd.atm.models;

public class TransactionType {

    private int transactionTypeId;
    private String typeName;

    public TransactionType() {
    }

    public TransactionType(String typeName) {
        this.typeName = typeName;
    }

    public int getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(int transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return typeName;
    }
}
