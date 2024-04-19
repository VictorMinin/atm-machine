package com.solvd.atm.enums;

public enum CardStatusEnum {
    ACTIVE("active"),
    BLOCKED("blocked");

    private final String cardStatus;

    CardStatusEnum(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getCardStatus() {
        return cardStatus;
    }
}
