package com.solvd.atm.utils.customexceptions;

public class SelfTransferException extends Exception {

    public SelfTransferException(String message) {
        super(message);
    }
}
