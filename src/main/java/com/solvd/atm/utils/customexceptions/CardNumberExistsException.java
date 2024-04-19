package com.solvd.atm.utils.customexceptions;

public class CardNumberExistsException extends Exception {
    public CardNumberExistsException(String message) {
        super(message);
    }
}
