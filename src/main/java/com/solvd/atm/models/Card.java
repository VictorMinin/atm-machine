package com.solvd.atm.models;

public class Card {
    private int cardId;
    private String cardNumber;
    private String pinNumber;
    private String expirationDate;
    private String cvc;
    private CardType cardType;
    private Account account;
    private CardStatus cardStatus;

    public Card() {
    }

    public Card(String cardNumber, String pinNumber, String expirationDate, String cvc, CardType cardType,
                Account account, CardStatus cardStatus) {
        this.cardNumber = cardNumber;
        this.pinNumber = pinNumber;
        this.expirationDate = expirationDate;
        this.cvc = cvc;
        this.cardType = cardType;
        this.account = account;
        this.cardStatus = cardStatus;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(String pinNumber) {
        this.pinNumber = pinNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    @Override
    public String toString() {
        return "ID: " + cardId +
                "\nCard Number: " + cardNumber +
                "\nExpiration Date: " + expirationDate +
                "\nCVC: " + cvc +
                "\nCard Type: " + cardType.getTypeName() +
                "\nAccount: " + account +
                "\nCard Status: " + cardStatus.getCardStatus();
    }
}
