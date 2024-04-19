package com.solvd.atm.utils.atm;

import com.solvd.atm.enums.AtmState;

import java.util.Calendar;
import java.util.regex.Pattern;

public class InputHandler {

    private static final Pattern CARD_NUMBER_PATTERN = Pattern.compile("\\d{4}-\\d{4}-\\d{4}-\\d{4}");
    private static final Pattern CARD_EXPIRATION_PATTERN = Pattern.compile("^\\d{2}/\\d{2}$");
    private static final Pattern CARD_CVC_PATTERN = Pattern.compile("\\d{3}");
    private static final Pattern CARD_PIN_PATTERN = Pattern.compile("\\d{4}");
    private static final Pattern AMOUNT_PATTERN = Pattern.compile("^[1-9]\\d*(\\.\\d{1,2})?$");
    private static final Pattern CARD_ID_PATTERN = Pattern.compile("\\d+");
    private static final Pattern USER_NAME_PATTERN = Pattern.compile("^[A-Za-z]+ [A-Za-z]+$");

    static public boolean checkCardId(String cardId) {
        if (cardId.equals("q")) {
            AtmScreen.printQuitMessage();
            return false;
        } else if (isInputValid(cardId, CARD_ID_PATTERN) && isIntParsable(cardId)) {
            return true;
        }
        AtmScreen.printIncorrectInputMessage();
        return false;
    }

    static public boolean checkUserName(String userName) {
        if (userName.equals("q")) {
            AtmScreen.printQuitMessage();
            return false;
        } else {
            return isInputValid(userName, USER_NAME_PATTERN);
        }
    }

    static public AtmState checkAdminAtmAction(String adminAtmAction) {
        AtmState atmState;
        switch (adminAtmAction) {
            case "1":
                atmState = AtmState.DEPOSIT;
                break;
            case "2":
                atmState = AtmState.WITHDRAWAL;
                break;
            case "3":
                atmState = AtmState.CHANGE_PIN;
                break;
            case "4":
                atmState = AtmState.CHECK_BALANCE;
                break;
            case "5":
                atmState = AtmState.MAKE_TRANSFER;
                break;
            case "6":
                atmState = AtmState.VIEW_ALL_TRANSACTION_HISTORY;
                break;
            case "7":
                atmState = AtmState.CHANGE_BLOCKED_CARDS;
                break;
            case "8":
                atmState = AtmState.VIEW_ACCOUNT;
                break;
            case "9":
                atmState = AtmState.VIEW_ALL_ACCOUNTS;
                break;
            case "10":
                atmState = AtmState.VIEW_CARD;
                break;
            case "11":
                atmState = AtmState.VIEW_ALL_CARDS;
                break;
            case "12":
                atmState = AtmState.VIEW_ALL_USERS;
                break;
            case "13":
                atmState = AtmState.CREATE_USER;
                break;
            case "14":
                atmState = AtmState.ADD_CARD_TO_USER;
                break;
            case "q":
                AtmScreen.printQuitMessage();
                atmState = AtmState.INITIAL;
                break;
            default:
                AtmScreen.printInvalidActionMessage();
                atmState = AtmState.PIN_VALIDATED;
                break;
        }
        return atmState;
    }

    static public boolean checkAmount(String amount) {
        if (amount.equals("q")) {
            AtmScreen.printQuitMessage();
            return false;
        } else if (isInputValid(amount, AMOUNT_PATTERN) && isDoubleParsable(amount)) {
            return true;
        }
        AtmScreen.printIncorrectInputMessage();
        return false;
    }

    static public boolean checkCardCvc(String cardCvc) {
        if (cardCvc.equals("q")) {
            AtmScreen.printQuitMessage();
            return false;
        } else if (isInputValid(cardCvc, CARD_CVC_PATTERN)) {
            return true;
        }
        AtmScreen.printIncorrectInputMessage();
        return false;
    }

    static public boolean checkCardPin(String cardPin) {
        if (cardPin.equals("q")) {
            AtmScreen.printQuitMessage();
            return false;
        } else if (isInputValid(cardPin, CARD_PIN_PATTERN)) {
            return true;
        }
        AtmScreen.printIncorrectInputMessage();
        return false;
    }

    static public boolean checkCardExpirationDate(String date) {
        String[] parts = date.split("/");
        if (parts.length != 2) {
            return false;
        }
        int month;
        int year;
        try {
            month = Integer.parseInt(parts[0]);
            year = Integer.parseInt(parts[1]) + 2000;
        } catch (NumberFormatException e) {
            return false;
        }
        if (month < 1 || month > 12) {
            return false;
        }
        Calendar now = Calendar.getInstance();
        int currentYear = now.get(Calendar.YEAR);
        int currentMonth = now.get(Calendar.MONTH) + 1;
        return year >= currentYear && (year != currentYear || month >= currentMonth) && isInputValid(date, CARD_EXPIRATION_PATTERN);
    }

    static public AtmState checkUserAtmAction(String userAtmAction) {
        AtmState atmState;
        switch (userAtmAction) {
            case "1":
                atmState = AtmState.DEPOSIT;
                break;
            case "2":
                atmState = AtmState.WITHDRAWAL;
                break;
            case "3":
                atmState = AtmState.CHANGE_PIN;
                break;
            case "4":
                atmState = AtmState.CHECK_BALANCE;
                break;
            case "5":
                atmState = AtmState.MAKE_TRANSFER;
                break;
            case "6":
                atmState = AtmState.VIEW_TRANSACTION_HISTORY;
                break;
            case "q":
                AtmScreen.printQuitMessage();
                atmState = AtmState.INITIAL;
                break;
            default:
                AtmScreen.printInvalidActionMessage();
                atmState = AtmState.PIN_VALIDATED;
                break;
        }
        return atmState;
    }

    static public boolean checkCardNumber(String cardNumber) {
        if (cardNumber.equals("q")) {
            AtmScreen.printQuitMessage();
            return false;
        } else if (isInputValid(cardNumber, CARD_NUMBER_PATTERN)) {
            return true;
        }
        AtmScreen.printIncorrectInputMessage();
        return false;
    }

    static public boolean checkIdleInput(String idleInput) {
        switch (idleInput) {
            case "q" :
                AtmScreen.printQuitMessage();
                return false;
            case "n" :
                return false;
            case "y" :
                return true;
            default :
                AtmScreen.printIncorrectInputMessage();
                return false;
        }
    }

    static public boolean checkCardTypeId(String cardType) {
        switch (cardType) {
            case "1":
            case "2":
                return true;
            case "q":
                AtmScreen.printQuitMessage();
                return false;
            default:
                AtmScreen.printIncorrectInputMessage();
                return false;
        }
    }

    static private boolean isInputValid(String input, Pattern pattern) {
        return input != null && pattern.matcher(input).matches();
    }

    static private boolean isIntParsable(String input) {
        if (input == null) {
            return false;
        }
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    static private boolean isDoubleParsable(String input) {
        if (input == null) {
            return false;
        }
        try {
            double value = Double.parseDouble(input);
            return value < Double.MAX_VALUE;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
