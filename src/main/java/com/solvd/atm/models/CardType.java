package com.solvd.atm.models;

public class CardType {

    private int cardTypeId;
    private String typeName;

    public CardType() {
    }

    public CardType(String typeName) {
        this.typeName = typeName;
    }

    public int getCardTypeId() {
        return cardTypeId;
    }

    public void setCardTypeId(int cardTypeId) {
        this.cardTypeId = cardTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "Type Name: " + typeName;
    }
}
