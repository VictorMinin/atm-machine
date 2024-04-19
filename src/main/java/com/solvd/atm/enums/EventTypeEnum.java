package com.solvd.atm.enums;

public enum EventTypeEnum {
    WITHDRAW_OPERATION("Withdraw operation done"),
    DEPOSIT_OPERATION("Deposit operation done"),
    TRANSACTION_OPERATION("Transaction is successful");

    private final String eventType;

    EventTypeEnum(String eventType) {
        this.eventType = eventType;
    }

    public String getEventType() {
        return eventType;
    }
}
