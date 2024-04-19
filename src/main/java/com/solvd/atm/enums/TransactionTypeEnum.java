package com.solvd.atm.enums;

public enum TransactionTypeEnum {
    DEPOSIT("deposit"),
    WITHDRAW("withdraw"),
    TRANSFER("transfer");

    private final String transactionType;

    TransactionTypeEnum(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionType() {
        return transactionType;
    }
}
