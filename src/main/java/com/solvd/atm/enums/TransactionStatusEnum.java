package com.solvd.atm.enums;

public enum TransactionStatusEnum {
    COMPLETE("complete"),
    BLOCKED("blocked");

    private final String status;

    TransactionStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
