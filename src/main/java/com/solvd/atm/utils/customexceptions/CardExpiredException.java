package com.solvd.atm.utils.customexceptions;

public class CardExpiredException extends Exception {
    public CardExpiredException(String message) {
        super(message);
    }
}
