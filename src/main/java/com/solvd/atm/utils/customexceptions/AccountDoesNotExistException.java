package com.solvd.atm.utils.customexceptions;

public class AccountDoesNotExistException extends Exception {

    public AccountDoesNotExistException(String message) {
        super(message);
    }

}
