package com.solvd.atm.utils.customexceptions;

public class TooManyPinFailuresException extends Exception {
    public TooManyPinFailuresException(String message) {
        super(message);
    }
}
