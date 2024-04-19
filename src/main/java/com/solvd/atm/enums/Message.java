package com.solvd.atm.enums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum Message {
    WELCOME(TextColor.MAGENTA.getColorCode() + "Welcome to Solvd ATM\npress q at anytime to return to homescreen\n"),
    CARD_NUMBER_PROMPT("Please enter in card number in this format XXXX-XXXX-XXXX-XXXX: \n"),
    EXPIRATION_PROMPT("Please enter in expiration date in this format MM/YY: \n"),
    CVC_PROMPT("Please enter in cvc in this format XXX: \n"),
    IDLE_PROMPT("Would you like to do another ATM action y/n?: \n"),
    PIN_PROMPT("Please enter in pin in this format XXXX: \n"),
    USER_ACTION_PROMPT(TextColor.BLUE.getColorCode() + "Please enter in your desired ATM action\n" + "-- q. Return to home screen -- 1. Deposit -- " +
            "2. Withdrawal -- 3. Change Pin -- 4. Check Balance -- 5. Make Transfer -- 6. View Transaction History --\n"),
    USER_CARD_PROMPT("Please enter in the card id whose status you want to change: \n"),
    ADMIN_ACTION_PROMPT(TextColor.BLUE.getColorCode() + "Please enter in your desired ATM action\n" + "-- q. Return to home screen -- 1. Deposit -- " +
            "2. Withdrawal -- 3. Change Pin -- 4. Check Balance -- " + "5. Make Transfer -- 6. View All Transaction History --\n-- 7. Change Blocked Cards --" +
            " 8. View Account By Id -- 9. View All Accounts -- 10. View Card By Id -- 11. View All Cards -- 12. View All Users -- " +
            "13. Create User With Card -- 14. Add Card to Existing User --\n"),
    DEPOSIT_SUCCESSFUL(TextColor.GREEN.getColorCode() + "Deposit Successful!\n" + TextColor.BLUE.getColorCode()),
    DEPOSIT_AMOUNT_PROMPT("Enter in desired dollar amount of deposit: \n"),
    WITHDRAWAL_SUCCESSFUL(TextColor.GREEN.getColorCode() + "Withdrawal Successful!\n" + TextColor.BLUE.getColorCode()),
    WITHDRAWAL_AMOUNT_PROMPT("Enter in desired dollar amount of withdrawal: \n"),
    PIN_CHANGE_SUCCESSFUL(TextColor.GREEN.getColorCode() + "Pin number change successful!\n" + TextColor.BLUE.getColorCode()),
    NEW_PIN_PROMPT("Please enter in your new pin in this format XXXX: \n"),
    CONFIRM_PIN_PROMPT("Please confirm your pin: \n"),
    CARD_BALANCE("Card balance amount: \n"),
    TRANSFER_AMOUNT_PROMPT("Enter in transfer amount: \n"),
    TRANSFER_SUCCESSFUL(TextColor.GREEN.getColorCode() + "Transfer successful!\n" + TextColor.BLUE.getColorCode()),
    BLOCK_CARD_PROMPT("Please enter in your desired ATM action\n " +
            "-- q. Return to home screen -- 1. Block a user's card -- 2. Unblock a user's card --\n"),
    ALL_ACCOUNTS("All Accounts: \n"),
    ALL_TRANSACTIONS("All Transaction History:\n"),
    ALL_CARDS("All Cards: \n"),
    TRANSACTIONS("Transaction History:\n"),
    TRANSFER_CARD_PROMPT("Enter in recipient's card number in this format XXXX-XXXX-XXXX-XXXX: \n"),
    QUIT("Returning to home screen\n"),
    INVALID_ACTION(TextColor.RED.getColorCode() + "Invalid action selected, try again\n" + TextColor.BLUE.getColorCode()),
    UNBLOCK_SUCCESSFUL(TextColor.GREEN.getColorCode() + "Card successfully unblocked!" + TextColor.BLUE.getColorCode()),
    BLOCK_SUCCESSFUL(TextColor.GREEN.getColorCode() + "Card successfully blocked!" + TextColor.BLUE.getColorCode()),
    INCORRECT_INPUT(TextColor.RED.getColorCode() + "Incorrect input, try again" + TextColor.BLUE.getColorCode()),
    VIEW_ACCOUNT_PROMPT("Enter in the account Id you want to view:\n"),
    VIEW_CARD_PROMPT("Enter in the card Id you want to view:\n"),
    CREATE_USER_NAME_PROMPT("Enter in the name of the new user:\n"),
    CREATE_USER_ID_PROMPT("Enter in the user type Id -- 1. User -- 2. Admin --\n"),
    CREATE_CARD_TYPE_PROMPT("Enter in the card type Id -- 1. Credit -- 2. Debit --\n");

    private static final Logger LOGGER = LogManager.getLogger(Message.class);
    private String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void printMessage() {
        LOGGER.info(this.getMessage());
    }

    public static void printExceptionMessage(String message) {
        LOGGER.info(TextColor.RED.getColorCode() + message + TextColor.BLUE.getColorCode());
    }

    public static void printMessage(double amount) {
        LOGGER.info(amount);
    }

    public static void printMessage(String s) {
        LOGGER.info(s);
    }
}
