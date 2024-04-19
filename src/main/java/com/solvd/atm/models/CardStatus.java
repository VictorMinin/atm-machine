package com.solvd.atm.models;

public class CardStatus {

    private int cardStatusId;
    private String cardStatus;

    public CardStatus() {
    }

    public CardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public int getCardStatusId() {
        return cardStatusId;
    }

    public void setCardStatusId(int cardStatusId) {
        this.cardStatusId = cardStatusId;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    @Override
    public String toString() {
        return "Card Status: " + cardStatus;
    }
}
