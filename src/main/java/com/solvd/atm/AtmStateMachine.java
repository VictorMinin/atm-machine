package com.solvd.atm;

import com.solvd.atm.enums.AtmState;
import com.solvd.atm.enums.TransactionStatusEnum;
import com.solvd.atm.enums.UserTypeEnum;
import com.solvd.atm.models.Account;
import com.solvd.atm.models.Card;
import com.solvd.atm.jackson.JacksonMarshaller;
import com.solvd.atm.models.Transaction;
import com.solvd.atm.utils.atm.ATM;
import com.solvd.atm.utils.atm.AtmScreen;
import com.solvd.atm.utils.atm.InputHandler;
import com.solvd.atm.utils.customexceptions.*;

import java.sql.SQLException;
import java.util.Scanner;

public class AtmStateMachine {

    public static void main(String[] args) {
        AtmState atmState = AtmState.INITIAL;
        Scanner scanner = new Scanner(System.in);
        boolean atmActive = true;
        ATM atmEventHandler = new ATM();
        while (atmActive) {
            switch (atmState) {
                case INITIAL:
                    AtmScreen.printWelcomeMessage();
                    AtmScreen.printCardNumberPrompt();
                    String cardNumber = scanner.nextLine();
                    boolean cardNumberValid = InputHandler.checkCardNumber(cardNumber);
                    if (!cardNumberValid) {
                        atmState = AtmState.INITIAL;
                        break;
                    }
                    try {
                        cardNumberValid = atmEventHandler.checkCardNumber(cardNumber, true);
                    } catch (CardDoesNotExistException | CardExpiredException e) {
                        AtmScreen.printExceptionMessage(e.getMessage());
                        atmState = AtmState.INITIAL;
                        break;
                    }
                    if (cardNumberValid) {
                        atmState = AtmState.CARD_VALIDATED;
                    } else {
                        atmState = AtmState.INITIAL;
                    }
                    break;

                case IDLE:
                    AtmScreen.printIdlePrompt();
                    String idleInput = scanner.nextLine();
                    boolean idleInputYes = InputHandler.checkIdleInput(idleInput);
                    if (idleInputYes) {
                        atmState = AtmState.PIN_VALIDATED;
                        break;
                    } else if (!(idleInput.equals("n") || idleInput.equals("q"))) {
                        atmState = AtmState.IDLE;
                    } else {
                        atmState = AtmState.INITIAL;
                    }
                    break;

                case CARD_VALIDATED:
                    AtmScreen.printPinPrompt();
                    String pin = scanner.nextLine();
                    boolean pinValid = InputHandler.checkCardPin(pin);
                    if (!pinValid) {
                        atmState = AtmState.INITIAL;
                        break;
                    }
                    try {
                        pinValid = atmEventHandler.checkPin(pin);
                    } catch (TooManyPinFailuresException e) {
                        AtmScreen.printExceptionMessage(e.getMessage());
                        atmState = AtmState.INITIAL;
                        break;
                    } catch (InvalidPinException e) {
                        AtmScreen.printExceptionMessage(e.getMessage());
                        atmState = AtmState.CARD_VALIDATED;
                        break;
                    }
                    if (pinValid) {
                        atmState = AtmState.PIN_VALIDATED;
                    } else {
                        atmState = AtmState.CARD_VALIDATED;
                    }
                    break;

                case PIN_VALIDATED:
                    String userType = atmEventHandler.checkUserType();
                    if (userType.equals(UserTypeEnum.USER.getUserType())) {
                        AtmScreen.printUserActionPrompt();
                        String userAtmAction = scanner.nextLine();
                        atmState = InputHandler.checkUserAtmAction(userAtmAction);
                    } else {
                        AtmScreen.printAdminActionPrompt();
                        String adminAtmAction = scanner.nextLine();
                        atmState = InputHandler.checkAdminAtmAction(adminAtmAction);
                    }
                    break;

                case DEPOSIT:
                    AtmScreen.printDepositAmountPrompt();
                    String depositAmount = scanner.nextLine();
                    boolean depositAmountValid = InputHandler.checkAmount(depositAmount);
                    if (!depositAmountValid) {
                        if (depositAmount.equals("q")) {
                            atmState = AtmState.INITIAL;
                        } else {
                            atmState = AtmState.DEPOSIT;
                        }
                        break;
                    }
                    Transaction depositTransaction;
                    try {
                        depositTransaction = atmEventHandler.cashDeposit(Double.parseDouble(depositAmount));
                        AtmScreen.printDepositSuccessfulMessage();
                        AtmScreen.printReceipt(depositTransaction);
                        JacksonMarshaller.writeFile(depositTransaction);
                        atmState = AtmState.IDLE;
                    } catch (BlockedCardException e) {
                        AtmScreen.printExceptionMessage(e.getMessage());
                        atmState = AtmState.INITIAL;
                    } catch (PastMaxCreditException e) {
                        AtmScreen.printExceptionMessage(e.getMessage());
                        atmState = AtmState.DEPOSIT;
                    }
                    break;

                case WITHDRAWAL:
                    AtmScreen.printWithdrawalAmountPrompt();
                    String withdrawalAmount = scanner.nextLine();
                    boolean withdrawalAmountValid = InputHandler.checkAmount(withdrawalAmount);
                    if (!withdrawalAmountValid) {
                        if (withdrawalAmount.equals("q")) {
                            atmState = AtmState.INITIAL;
                            break;
                        }
                        atmState = AtmState.WITHDRAWAL;
                        break;
                    }
                    Transaction withdrawalTransaction;
                    try {
                        withdrawalTransaction = atmEventHandler.cashWithdrawal(Double.parseDouble(withdrawalAmount));
                        if (!withdrawalTransaction.getTransactionStatus().getStatusName().equals(TransactionStatusEnum.BLOCKED.getStatus())) {
                            AtmScreen.printWithdrawalSuccessfulMessage();
                            AtmScreen.printReceipt(withdrawalTransaction);
                            JacksonMarshaller.writeFile(withdrawalTransaction);
                            atmState = AtmState.IDLE;
                        } else {
                            AtmScreen.printReceipt(withdrawalTransaction);
                            JacksonMarshaller.writeFile(withdrawalTransaction);
                            atmState = AtmState.WITHDRAWAL;
                        }
                    } catch (InsufficientFundsException | BlockedCardException e) {
                        AtmScreen.printExceptionMessage(e.getMessage());
                        atmState = AtmState.WITHDRAWAL;
                    }
                    break;

                case CHANGE_PIN:
                    AtmScreen.printNewPinPrompt();
                    String firstPin = scanner.nextLine();
                    boolean firstPinValid = InputHandler.checkCardPin(firstPin);
                    if (!firstPinValid) {
                        if (firstPin.equals("q")) {
                            atmState = AtmState.INITIAL;
                            break;
                        }
                        atmState = AtmState.CHANGE_PIN;
                        break;
                    }
                    AtmScreen.printConfirmPinPrompt();
                    String secondPin = scanner.nextLine();
                    boolean secondPinValid = InputHandler.checkCardPin(secondPin);
                    if (!secondPinValid) {
                        if (secondPin.equals("q")) {
                            atmState = AtmState.INITIAL;
                            break;
                        }
                        atmState = AtmState.CHANGE_PIN;
                        break;
                    }
                    try {
                        if (firstPin.equals(secondPin)) {
                            atmEventHandler.changePin(firstPin);
                            AtmScreen.printPinChangeSuccessfulMessage();
                            atmState = AtmState.IDLE;
                        } else {
                            throw new PinMismatchException("Pins are different");
                        }
                    } catch (PinMismatchException e) {
                        AtmScreen.printExceptionMessage(e.getMessage());
                        atmState = AtmState.CHANGE_PIN;
                    }
                    break;

                case CHECK_BALANCE:
                    double amount = atmEventHandler.checkBalance();
                    AtmScreen.printCardBalanceMessage(amount);
                    atmState = AtmState.IDLE;
                    break;

                case MAKE_TRANSFER:
                    AtmScreen.printRecipientCardNumberPrompt();
                    String cardNumberString = scanner.nextLine();
                    cardNumberValid = InputHandler.checkCardNumber(cardNumberString);
                    boolean cardExists = false;
                    if (cardNumberValid) {
                        try {
                            cardExists = atmEventHandler.checkCardNumber(cardNumberString, false);
                        } catch (CardDoesNotExistException | CardExpiredException e) {
                            AtmScreen.printExceptionMessage(e.getMessage());
                            atmState = AtmState.MAKE_TRANSFER;
                            break;
                        }
                    }
                    if (!cardNumberValid || !cardExists) {
                        if (cardNumberString.equals("q")) {
                            atmState = AtmState.INITIAL;
                            break;
                        }
                        atmState = AtmState.MAKE_TRANSFER;
                        break;
                    }
                    AtmScreen.printTransferAmountPrompt();
                    String transferAmount = scanner.nextLine();
                    boolean transferAmountValid = InputHandler.checkAmount(transferAmount);
                    if (!transferAmountValid) {
                        if (transferAmount.equals("q")) {
                            atmState = AtmState.INITIAL;
                            break;
                        }
                        atmState = AtmState.MAKE_TRANSFER;
                        break;
                    }
                    Transaction transferTransaction;
                    try {
                        transferTransaction = atmEventHandler.moneyTransfer(cardNumberString, Double.parseDouble(transferAmount));
                        if (transferTransaction.getTransactionStatus().getStatusName().equals(TransactionStatusEnum.COMPLETE.getStatus())) {
                            AtmScreen.printTransferSuccessfulMessage();
                            AtmScreen.printReceipt(transferTransaction);
                            JacksonMarshaller.writeFile(transferTransaction);
                            atmState = AtmState.IDLE;
                        } else {
                            JacksonMarshaller.writeFile(transferTransaction);
                            AtmScreen.printReceipt(transferTransaction);
                            atmState = AtmState.MAKE_TRANSFER;
                        }
                    } catch (SQLException | InsufficientFundsException | BlockedCardException | SelfTransferException e) {
                        AtmScreen.printExceptionMessage(e.getMessage());
                        atmState = AtmState.MAKE_TRANSFER;
                    }
                    break;

                case VIEW_TRANSACTION_HISTORY:
                    AtmScreen.printTransactionsMessage();
                    AtmScreen.printTable(atmEventHandler.seeTransactionHistory(), "Transaction History");
                    atmState = AtmState.IDLE;
                    break;

                case VIEW_ALL_TRANSACTION_HISTORY:
                    AtmScreen.printAllTransactionsMessage();
                    AtmScreen.printTable(atmEventHandler.seeAllTransactions(), "All transactions");
                    atmState = AtmState.IDLE;
                    break;

                case VIEW_ALL_ACCOUNTS:
                    AtmScreen.printAllAccountsMessage();
                    AtmScreen.printTable(atmEventHandler.seeAllAccounts(), "All accounts");
                    atmState = AtmState.IDLE;
                    break;

                case VIEW_ALL_CARDS:
                    AtmScreen.printAllCardsMessage();
                    AtmScreen.printTable(atmEventHandler.seeAllCards(), "All cards");
                    atmState = AtmState.IDLE;
                    break;

                case CHANGE_BLOCKED_CARDS:
                    AtmScreen.printBlockCardChoicePrompt();
                    String choice = scanner.nextLine();
                    String cardIdString;
                    boolean cardIdValid;
                    switch (choice) {
                        case "1":
                            AtmScreen.printUserCardPrompt();
                            cardIdString = scanner.nextLine();
                            cardIdValid = InputHandler.checkCardId(cardIdString);
                            if (!cardIdString.equals("q") && cardIdValid) {
                                cardExists = atmEventHandler.checkCardId(Integer.parseInt(cardIdString));
                                if (cardExists) {
                                    atmEventHandler.blockCard(Integer.parseInt(cardIdString));
                                    AtmScreen.printBlockSuccessfulMessage();
                                    atmState = AtmState.IDLE;
                                }
                            } else {
                                if (cardIdString.equals("q")) {
                                    atmState = AtmState.INITIAL;
                                    break;
                                }
                            }
                            break;

                        case "2":
                            AtmScreen.printUserCardPrompt();
                            cardIdString = scanner.nextLine();
                            cardIdValid = InputHandler.checkCardId(cardIdString);
                            if (!cardIdString.equals("q") && cardIdValid) {
                                cardExists = atmEventHandler.checkCardId(Integer.parseInt(cardIdString));
                                if (cardExists) {
                                    atmEventHandler.unblockCard(Integer.parseInt(cardIdString));
                                    AtmScreen.printUnblockSuccessfulMessage();
                                    atmState = AtmState.IDLE;
                                }
                            } else {
                                if (cardIdString.equals("q")) {
                                    atmState = AtmState.INITIAL;
                                    break;
                                }
                            }
                            break;

                        case "q":
                            AtmScreen.printQuitMessage();
                            atmState = AtmState.INITIAL;
                            break;

                        default:
                            AtmScreen.printIncorrectInputMessage();
                            atmState = AtmState.CHANGE_BLOCKED_CARDS;
                            break;
                    }
                    break;

                case VIEW_ACCOUNT:
                    AtmScreen.printViewAccountPrompt();
                    String accountId = scanner.nextLine();
                    try {
                        if (!InputHandler.checkCardId(accountId)) {
                            atmState = AtmState.INITIAL;
                            break;
                        }
                        Account account = atmEventHandler.seeAccountById(Integer.parseInt(accountId));
                        AtmScreen.printMessage(account.toString());
                    } catch (AccountDoesNotExistException e) {
                        AtmScreen.printExceptionMessage(e.getMessage());
                        atmState = AtmState.VIEW_ACCOUNT;
                        break;
                    }
                    atmState = AtmState.IDLE;
                    break;

                case VIEW_CARD:
                    AtmScreen.printViewCardPrompt();
                    String cardId = scanner.nextLine();
                    try {
                        if (!InputHandler.checkCardId(cardId)) {
                            atmState = AtmState.INITIAL;
                            break;
                        }
                        Card card = atmEventHandler.seeCardByID(Integer.parseInt(cardId));
                        AtmScreen.printMessage(card.toString());
                    } catch (CardDoesNotExistException e) {
                        AtmScreen.printExceptionMessage(e.getMessage());
                        atmState = AtmState.VIEW_CARD;
                        break;
                    }
                    atmState = AtmState.IDLE;
                    break;

                case CREATE_USER:
                    AtmScreen.printUserNamePrompt();
                    String userName = scanner.nextLine();
                    boolean userNameValid = InputHandler.checkUserName(userName);
                    boolean userNameExists = atmEventHandler.checkUserNameExist(userName);
                    if (!userNameValid | userNameExists) {
                        if (userName.equals("q")) {
                            atmState = AtmState.INITIAL;
                        } else {
                            AtmScreen.printIncorrectInputMessage();
                            atmState = AtmState.CREATE_USER;
                        }
                        break;
                    }
                    AtmScreen.printUserTypeIdPrompt();
                    String newUserTypeId = scanner.nextLine();
                    switch (newUserTypeId) {
                        case "1":
                        case "2":
                            AtmScreen.printCardNumberPrompt();
                            String newCardNumber = scanner.nextLine();
                            cardNumberValid = InputHandler.checkCardNumber(newCardNumber);
                            if (!cardNumberValid) {
                                if (newCardNumber.equals("q")) {
                                    atmState = AtmState.INITIAL;
                                } else {
                                    AtmScreen.printIncorrectInputMessage();
                                    atmState = AtmState.CREATE_USER;
                                }
                                break;
                            }
                            AtmScreen.printPinPrompt();
                            String pinNumber = scanner.nextLine();
                            pinValid = InputHandler.checkCardPin(pinNumber);
                            if (!pinValid) {
                                if (pinNumber.equals("q")) {
                                    atmState = AtmState.INITIAL;
                                } else {
                                    AtmScreen.printIncorrectInputMessage();
                                    atmState = AtmState.CREATE_USER;
                                }
                                break;
                            }
                            AtmScreen.printExpirationPrompt();
                            String expirationDate = scanner.nextLine();
                            boolean expirationDateValid = InputHandler.checkCardExpirationDate(expirationDate);
                            if (!expirationDateValid) {
                                if (expirationDate.equals("q")) {
                                    atmState = AtmState.INITIAL;
                                } else {
                                    AtmScreen.printIncorrectInputMessage();
                                    atmState = AtmState.CREATE_USER;
                                }
                                break;
                            }
                            AtmScreen.printCvcPrompt();
                            String cvc = scanner.nextLine();
                            boolean cvcValid = InputHandler.checkCardCvc(cvc);
                            if (!cvcValid) {
                                if (cvc.equals("q")) {
                                    atmState = AtmState.INITIAL;
                                } else {
                                    AtmScreen.printIncorrectInputMessage();
                                    atmState = AtmState.CREATE_USER;
                                }
                                break;
                            }
                            AtmScreen.printCardTypePrompt();
                            String cardType = scanner.nextLine();
                            boolean cardTypeValid = InputHandler.checkCardTypeId(cardType);
                            if (!cardTypeValid) {
                                if (cardType.equals("q")) {
                                    atmState = AtmState.INITIAL;
                                } else {
                                    AtmScreen.printIncorrectInputMessage();
                                    atmState = AtmState.CREATE_USER;
                                }
                                break;
                            }
                            int cardTypeId = Integer.parseInt(cardType);
                            try {
                                atmEventHandler.issueCard(userName, newCardNumber, pinNumber, expirationDate, cvc, Integer.parseInt(newUserTypeId), cardTypeId);
                            } catch (CardNumberExistsException e) {
                                AtmScreen.printExceptionMessage(e.getMessage());
                            }
                            atmState = AtmState.IDLE;
                            break;
                        case "q":
                            atmState = AtmState.INITIAL;
                            break;
                        default:
                            AtmScreen.printIncorrectInputMessage();
                            atmState = AtmState.CREATE_USER;
                            break;
                    }
                    break;

                case ADD_CARD_TO_USER:
                    AtmScreen.printUserNamePrompt();
                    userName = scanner.nextLine();
                    userNameValid = InputHandler.checkUserName(userName);
                    userNameExists = atmEventHandler.checkUserNameExist(userName);
                    if (!userNameValid | !userNameExists) {
                        if (userName.equals("q")) {
                            atmState = AtmState.INITIAL;
                        } else {
                            AtmScreen.printIncorrectInputMessage();
                            atmState = AtmState.ADD_CARD_TO_USER;
                        }
                        break;
                    }
                    AtmScreen.printCardNumberPrompt();
                    cardNumber = scanner.nextLine();
                    cardNumberValid = InputHandler.checkCardNumber(cardNumber);
                    if (!cardNumberValid) {
                        if (cardNumber.equals("q")) {
                            atmState = AtmState.INITIAL;
                        } else {
                            AtmScreen.printIncorrectInputMessage();
                            atmState = AtmState.ADD_CARD_TO_USER;
                        }
                        break;
                    }
                    AtmScreen.printPinPrompt();
                    String pinNumber = scanner.nextLine();
                    pinValid = InputHandler.checkCardPin(pinNumber);
                    if (!pinValid) {
                        if (pinNumber.equals("q")) {
                            atmState = AtmState.INITIAL;
                        } else {
                            AtmScreen.printIncorrectInputMessage();
                            atmState = AtmState.ADD_CARD_TO_USER;
                        }
                        break;
                    }
                    AtmScreen.printExpirationPrompt();
                    String expirationDate = scanner.nextLine();
                    boolean expirationDateValid = InputHandler.checkCardExpirationDate(expirationDate);
                    if (!expirationDateValid) {
                        if (expirationDate.equals("q")) {
                            atmState = AtmState.INITIAL;
                        } else {
                            AtmScreen.printIncorrectInputMessage();
                            atmState = AtmState.ADD_CARD_TO_USER;
                        }
                        break;
                    }
                    AtmScreen.printCvcPrompt();
                    String cvc = scanner.nextLine();
                    boolean cvcValid = InputHandler.checkCardCvc(cvc);
                    if (!cvcValid) {
                        if (cvc.equals("q")) {
                            atmState = AtmState.INITIAL;
                        } else {
                            AtmScreen.printIncorrectInputMessage();
                            atmState = AtmState.ADD_CARD_TO_USER;
                        }
                        break;
                    }
                    AtmScreen.printCardTypePrompt();
                    String cardType = scanner.nextLine();
                    boolean cardTypeValid = InputHandler.checkCardTypeId(cardType);
                    if (!cardTypeValid) {
                        if (cardType.equals("q")) {
                            atmState = AtmState.INITIAL;
                        } else {
                            AtmScreen.printIncorrectInputMessage();
                            atmState = AtmState.ADD_CARD_TO_USER;
                        }
                        break;
                    }
                    int cardTypeId = Integer.parseInt(cardType);
                    try {
                        int accountID = atmEventHandler.createAccountForExistingUser(userName, cardTypeId);
                        atmEventHandler.issueCardToExistingAccount(cardNumber, pinNumber, expirationDate, cvc, cardTypeId, accountID);
                    } catch (CardNumberExistsException e) {
                        AtmScreen.printExceptionMessage(e.getMessage());
                        atmState = AtmState.ADD_CARD_TO_USER;
                        break;
                    }
                    atmState = AtmState.IDLE;
                    break;

                case VIEW_ALL_USERS:
                    AtmScreen.printTable(atmEventHandler.seeAllUsers(), "All users");
                    atmState = AtmState.IDLE;
                    break;
            }
        }
        scanner.close();
    }
}