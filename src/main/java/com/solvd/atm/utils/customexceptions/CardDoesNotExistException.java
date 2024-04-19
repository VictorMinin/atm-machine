package com.solvd.atm.utils.customexceptions;

public class CardDoesNotExistException extends Exception {

    public CardDoesNotExistException(String message) {
        super(message);
    }
}
