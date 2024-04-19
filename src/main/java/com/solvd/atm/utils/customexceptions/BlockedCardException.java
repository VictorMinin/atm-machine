package com.solvd.atm.utils.customexceptions;

public class BlockedCardException extends Exception {

    public BlockedCardException(String message) {
        super(message);
    }
}
