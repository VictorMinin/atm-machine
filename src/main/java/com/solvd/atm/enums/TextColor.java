package com.solvd.atm.enums;

public enum TextColor {
    BLUE("\u001B[34m"),
    CYAN("\u001B[36m"),
    BLACK("\u001B[30m"),
    RED("\u001B[31m"),
    WHITE("\u001B[37m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    MAGENTA("\u001B[35m"),
    RESET("\u001B[0m");

    private final String colorCode;

    TextColor(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getColorCode() {
        return colorCode;
    }
}
