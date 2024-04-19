package com.solvd.atm.enums;

public enum PinErrorsEnum {
    MAX_PIN_ERRORS(3),
    RESET_ERRORS(0);

    private int count;

    PinErrorsEnum(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
