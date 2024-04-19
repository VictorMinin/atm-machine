package com.solvd.atm.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Transaction {

    @JsonProperty
    private int transactionId;

    @JsonProperty
    private TransactionStatus transactionStatus;

    @JsonProperty
    private TransactionType transactionType;

    @JsonProperty
    private Card senderCard;

    @JsonProperty
    private Card recipientCard;

    @JsonProperty
    private double amount;

    @JsonProperty
    private Event event;

    public Transaction() {
    }

    public Transaction(TransactionStatus transactionStatus, TransactionType transactionType, Card senderCard,
                       Card recipientCard, double amount, Event event) {
        this.transactionStatus = transactionStatus;
        this.transactionType = transactionType;
        this.senderCard = senderCard;
        this.recipientCard = recipientCard;
        this.amount = amount;
        this.event = event;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Card getSenderCard() {
        return senderCard;
    }

    public void setSenderCard(Card senderCard) {
        this.senderCard = senderCard;
    }

    public Card getRecipientCard() {
        return recipientCard;
    }

    public void setRecipientCard(Card recipientCard) {
        this.recipientCard = recipientCard;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        String transaction = "ID: " + transactionId +
                "\nTransaction Status: " + transactionStatus.getStatusName() +
                "\nTransaction Type: " + transactionType.getTypeName() +
                "\nSender Card: \n" + senderCard;
        if (recipientCard != null) {
            transaction += "\nRecipient Card: \n" + recipientCard +
                    "\nAmount: " + amount +
                    "\n" + event.getEventType();
        } else {
            transaction += "\nAmount: " + amount +
                    "\n" + event.getEventType();
        }
        return transaction;
    }
}
