package com.solvd.atm.models;

public class UserType {

    private int userTypeId;
    private String userType;

    public UserType() {
    }

    public UserType(String userType) {
        this.userType = userType;
    }

    public int getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return userType;
    }
}
