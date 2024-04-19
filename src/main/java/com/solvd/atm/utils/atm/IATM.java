package com.solvd.atm.utils.atm;

import com.solvd.atm.models.Account;
import com.solvd.atm.models.Card;
import com.solvd.atm.models.Transaction;
import com.solvd.atm.models.User;
import com.solvd.atm.utils.customexceptions.*;

import java.sql.SQLException;
import java.util.List;

public interface IATM {

    boolean checkCardNumber(String cardNumber, boolean setCurrentCard) throws CardDoesNotExistException, CardExpiredException;

    boolean checkCardId(int cardId) throws CardDoesNotExistException;

    void checkRecipient(int recipientCardId) throws SelfTransferException;

    boolean checkPin(String pin) throws InvalidPinException, TooManyPinFailuresException;

    String checkUserType();

    Transaction cashDeposit(double amount) throws BlockedCardException, PastMaxCreditException;

    Transaction cashWithdrawal(double amount) throws InsufficientFundsException, BlockedCardException;

    void changePin(String newPin) throws PinMismatchException;

    double checkBalance();

    Transaction moneyTransfer(String recipientCardNumber, double amount) throws SQLException, InsufficientFundsException, BlockedCardException, SelfTransferException;

    List<Transaction> seeTransactionHistory();

    void blockCard(int cardId); //Admin

    void unblockCard(int cardId); // Admin

    List<Transaction> seeAllTransactions(); //Admin

    List<Card> seeAllCards(); //Admin

    List<Account> seeAllAccounts(); //Admin

    Card seeCardByID(int id) throws CardDoesNotExistException; //Admin

    Account seeAccountById(int id) throws AccountDoesNotExistException; // Admin

    void issueCard(String name, String cardNumber, String pinNumber, String expirationDate, String cvc, int userTypeId, int cardTypeID) throws CardNumberExistsException; // Admin

    int createAccountForExistingUser(String userName, int cardTypeId); // Admin

    void issueCardToExistingAccount(String cardNumber, String pinNumber, String expirationDate, String cvc, int cardTypeID, int accountID) throws CardDoesNotExistException, CardNumberExistsException; // Admin

    List<User> seeAllUsers(); // Admin

    void createUser(String name, int userTypeID); // Admin

    boolean checkUserNameExist(String userName);
}
