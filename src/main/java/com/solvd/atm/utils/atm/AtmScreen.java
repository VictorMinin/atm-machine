package com.solvd.atm.utils.atm;

import com.solvd.atm.enums.Message;
import com.solvd.atm.models.Transaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AtmScreen {

    private static final Logger LOGGER = LogManager.getLogger(AtmScreen.class);

    public static void printWelcomeMessage() {
        Message.WELCOME.printMessage();
    }

    public static void printCardNumberPrompt() {
        Message.CARD_NUMBER_PROMPT.printMessage();
    }

    public static void printIdlePrompt() {
        Message.IDLE_PROMPT.printMessage();
    }

    public static void printPinPrompt() {
        Message.PIN_PROMPT.printMessage();
    }

    public static void printUserActionPrompt() {
        Message.USER_ACTION_PROMPT.printMessage();
    }

    public static void printAdminActionPrompt() {
        Message.ADMIN_ACTION_PROMPT.printMessage();
    }

    public static void printDepositSuccessfulMessage() {
        Message.DEPOSIT_SUCCESSFUL.printMessage();
    }

    public static void printDepositAmountPrompt() {
        Message.DEPOSIT_AMOUNT_PROMPT.printMessage();
    }

    public static void printWithdrawalSuccessfulMessage() {
        Message.WITHDRAWAL_SUCCESSFUL.printMessage();
    }

    public static void printWithdrawalAmountPrompt() {
        Message.WITHDRAWAL_AMOUNT_PROMPT.printMessage();
    }

    public static void printPinChangeSuccessfulMessage() {
        Message.PIN_CHANGE_SUCCESSFUL.printMessage();
    }

    public static void printNewPinPrompt() {
        Message.NEW_PIN_PROMPT.printMessage();
    }

    public static void printConfirmPinPrompt() {
        Message.CONFIRM_PIN_PROMPT.printMessage();
    }

    public static void printCardBalanceMessage(double amount) {
        Message.CARD_BALANCE.printMessage();
        Message.printMessage(amount);
    }

    public static void printRecipientCardNumberPrompt() {
        Message.TRANSFER_CARD_PROMPT.printMessage();
    }

    public static void printTransferAmountPrompt() {
        Message.TRANSFER_AMOUNT_PROMPT.printMessage();
    }

    public static void printTransferSuccessfulMessage() {
        Message.TRANSFER_SUCCESSFUL.printMessage();
    }

    public static void printBlockCardChoicePrompt() {
        Message.BLOCK_CARD_PROMPT.printMessage();
    }

    public static void printAllAccountsMessage() {
        Message.ALL_ACCOUNTS.printMessage();
    }

    public static void printTransactionsMessage() {
        Message.TRANSACTIONS.printMessage();
    }

    public static void printAllTransactionsMessage() {
        Message.ALL_TRANSACTIONS.printMessage();
    }

    public static void printAllCardsMessage() {
        Message.ALL_CARDS.printMessage();
    }

    public static void printUserCardPrompt() {
        Message.USER_CARD_PROMPT.printMessage();
    }

    public static void printQuitMessage() {
        Message.QUIT.printMessage();
    }

    public static void printInvalidActionMessage() {
        Message.INVALID_ACTION.printMessage();
    }

    public static void printUnblockSuccessfulMessage() {
        Message.UNBLOCK_SUCCESSFUL.printMessage();
    }

    public static void printBlockSuccessfulMessage() {
        Message.BLOCK_SUCCESSFUL.printMessage();
    }

    public static void printIncorrectInputMessage() {
        Message.INCORRECT_INPUT.printMessage();
    }

    public static void printViewAccountPrompt() {
        Message.VIEW_ACCOUNT_PROMPT.printMessage();
    }

    public static void printViewCardPrompt() {
        Message.VIEW_CARD_PROMPT.printMessage();
    }

    public static void printExceptionMessage(String message) {
        Message.printExceptionMessage(message);
    }

    public static void printReceipt(Transaction transaction) {
        LOGGER.info(PrettyFormatter.formatReceipt(transaction));
    }

    public static <T> void printTable(List<T> list, String listName) {
        LOGGER.info(PrettyFormatter.formatTable(list, listName));
    }

    public static void printMessage(String s) {
        Message.printMessage(s);
    }

    public static void printUserNamePrompt() {
        Message.CREATE_USER_NAME_PROMPT.printMessage();
    }

    public static void printUserTypeIdPrompt() {
        Message.CREATE_USER_ID_PROMPT.printMessage();
    }

    public static void printCvcPrompt() {
        Message.CVC_PROMPT.printMessage();
    }

    public static void printExpirationPrompt() {
        Message.EXPIRATION_PROMPT.printMessage();
    }

    public static void printCardTypePrompt() {
        Message.CREATE_CARD_TYPE_PROMPT.printMessage();
    }
}
