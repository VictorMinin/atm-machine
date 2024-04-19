package com.solvd.atm.models;

public class User {

    private int userId;
    private String name;
    private UserType userType;

    public User() {
    }

    public User(String name, UserType userType) {
        this.name = name;
        this.userType = userType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "\nID: " + userId + "\n" + name +
                "\nUser Type: " + userType;
    }
}
