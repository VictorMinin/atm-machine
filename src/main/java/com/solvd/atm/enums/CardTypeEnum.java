package com.solvd.atm.enums;

public enum CardTypeEnum {
    CREDIT("credit", 1),
    DEBIT("debit", 2);

    private final String cardType;
    private final int code;

    CardTypeEnum(String cardType, int code) {
        this.cardType = cardType;
        this.code = code;
    }

    public String getCardType() {
        return cardType;
    }

    public int getCode() {
        return code;
    }
}
