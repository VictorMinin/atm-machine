package com.solvd.atm.utils.customexceptions;

import java.security.PublicKey;

public class PastMaxCreditException extends Exception {

    public PastMaxCreditException(String message) {
        super(message);
    }
}
