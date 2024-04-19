package com.solvd.atm.enums;

public enum UserTypeEnum {
    USER("user", 1),
    ADMIN("admin", 2);

    private final String userType;
    private final int code;

    UserTypeEnum(String userType, int code) {
        this.userType = userType;
        this.code = code;
    }

    public String getUserType() {
        return userType;
    }

    public int getCode() {
        return code;
    }
}
