package com.solvd.atm.utils.customexceptions;

public class InvalidPinException extends Exception {

    public InvalidPinException(String message) {
        super(message);
    }
}
