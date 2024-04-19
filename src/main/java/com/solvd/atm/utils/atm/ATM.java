package com.solvd.atm.utils.atm;

import com.solvd.atm.enums.CardStatusEnum;
import com.solvd.atm.enums.CardTypeEnum;
import com.solvd.atm.enums.PinErrorsEnum;
import com.solvd.atm.enums.UserTypeEnum;
import com.solvd.atm.models.Account;
import com.solvd.atm.models.Card;
import com.solvd.atm.models.Transaction;
import com.solvd.atm.models.User;
import com.solvd.atm.service.*;
import com.solvd.atm.utils.SHA1;
import com.solvd.atm.utils.customexceptions.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ATM implements IATM {

    private static final Logger LOGGER = LogManager.getLogger(ATM.class);
    private Card currentCard;
    private Map<String, Integer> cardPinErrors = new HashMap<>();

    @Override
    public void checkRecipient(int recipientCardId) throws SelfTransferException {
        if (currentCard.getCardId() == recipientCardId) {
                LOGGER.debug("Self Transfer Exception for {}", currentCard.getCardId());
                LOGGER.error("error");
                throw new SelfTransferException("Cannot transfer to self\n");
            }
    }

    @Override
    public boolean checkCardNumber(String cardNumber, boolean setCurrentCard) throws CardDoesNotExistException, CardExpiredException {
        Card card = new CardService().getCardByCardNumber(cardNumber);
        if (setCurrentCard) {
            currentCard = card;
        }
        if (card == null) {
            LOGGER.debug("Card does not exists");
            throw new CardDoesNotExistException("Card does not exist\n");
        }
        YearMonth currentDate = YearMonth.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
        YearMonth cardExpDate = YearMonth.parse(currentCard.getExpirationDate(), formatter);
        if (cardExpDate.isBefore(currentDate)) {
            LOGGER.debug("Card expired exception for {}", card.getCardId());
            throw new CardExpiredException("Card is expired\n");
        }
        if (!cardPinErrors.containsKey(currentCard.getCardNumber())) {
            cardPinErrors.put(cardNumber, PinErrorsEnum.RESET_ERRORS.getCount());
        }
        return card.getCardNumber().equals(cardNumber);
    }

    @Override
    public boolean checkCardId(int cardId) {
        try {
            Card card = new CardService().getEntityById(cardId);
            if (card == null) {
                LOGGER.debug("Card does not exists");
                throw new CardDoesNotExistException("Card does not exist\n");
            }
        } catch (CardDoesNotExistException e) {
            AtmScreen.printExceptionMessage(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean checkPin(String pin) throws TooManyPinFailuresException, InvalidPinException {
        if (!currentCard.getPinNumber().equals(SHA1.encryptPin(pin))) {
            int errors = cardPinErrors.get(currentCard.getCardNumber());
            System.out.println(errors);
            cardPinErrors.put(currentCard.getCardNumber(), ++errors);
            if (cardPinErrors.get(currentCard.getCardNumber()) >= PinErrorsEnum.MAX_PIN_ERRORS.getCount()) {
                System.out.println(cardPinErrors.get(currentCard.getCardNumber()));
                blockCard(currentCard.getCardId());
                LOGGER.debug("Card blocked due to too many pin failures for {}", currentCard.getCardId());
                LOGGER.error("testing");
                throw new TooManyPinFailuresException("Card blocked due to too many pin failures");
            }
            LOGGER.debug("Invalid PIN number exception for {}", currentCard.getCardId());
            throw new InvalidPinException("Invalid PIN number\n");
        }
        cardPinErrors.put(currentCard.getCardNumber(), PinErrorsEnum.RESET_ERRORS.getCount());
        return true;
    }

    @Override
    public String checkUserType() {
        return currentCard.getAccount().getUser().getUserType().getUserType();
    }

    @Override
    public Transaction cashDeposit(double amount) throws BlockedCardException, PastMaxCreditException {
        CardService cardService = new CardService();
        Transaction transaction = cardService.depositToCard(currentCard.getCardId(), amount);
        return transaction;
    }

    @Override
    public Transaction cashWithdrawal(double amount) throws InsufficientFundsException, BlockedCardException {
        CardService cardService = new CardService();
        Transaction transaction = cardService.withdrawFromCard(currentCard.getCardId(), amount);
        return transaction;
    }

    @Override
    public void changePin(String newPin) {
        currentCard.setPinNumber(SHA1.encryptPin(newPin));
        LOGGER.debug("Pin Change for {}", currentCard.getCardId());
        new CardService().updateEntity(currentCard);
    }

    @Override
    public double checkBalance() {
        AccountService accountService = new AccountService();
        CardService cardService = new CardService();
        currentCard = cardService.getCardByCardNumber(currentCard.getCardNumber());
        Account account = accountService.getEntityById(currentCard.getAccount().getAccountId());
        return account.getAmount();
    }

    @Override
    public Transaction moneyTransfer(String recipientCardNumber, double amount) throws SQLException, InsufficientFundsException, BlockedCardException, SelfTransferException {
        TransactionService transactionService = new TransactionService();
        int recipientCardId = new CardService().getCardByCardNumber(recipientCardNumber).getCardId();
        checkRecipient(recipientCardId);
        Transaction transaction = transactionService.moneyTransfer(currentCard.getCardId(), recipientCardId, amount);
        currentCard = new CardService().getCardByCardNumber(currentCard.getCardNumber());
        LOGGER.debug("Money transfer from {} to {} ", currentCard.getCardId(), recipientCardId);
        return transaction;
    }

    @Override
    public List<Transaction> seeTransactionHistory() {
        return new TransactionService().getAllTransactionByCardId(currentCard.getCardId());
    }

    @Override // Admin
    public void blockCard(int cardId) {
        CardService cardService = new CardService();
        cardService.blockCardById(cardId);
        LOGGER.debug("Card blocked for {}", cardId);
    }

    @Override // Admin
    public void unblockCard(int cardId) {
        CardService cardService = new CardService();
        cardService.unblockCardById(cardId);
        LOGGER.info("Card unblocked for {}", cardId);
    }

    @Override // Admin
    public List<Transaction> seeAllTransactions() {
        return new TransactionService().getAll();
    }

    @Override // Admin
    public List<Card> seeAllCards() {
        return new CardService().getAll();
    }

    @Override // Admin
    public List<Account> seeAllAccounts() {
        return new AccountService().getAll();
    }

    @Override
    public Card seeCardByID(int id) throws CardDoesNotExistException {
        if (new CardService().getEntityById(id) == null) {
            throw new CardDoesNotExistException("Card does not exist");
        } else {
            return new CardService().getEntityById(id);
        }
    }

    @Override
    public Account seeAccountById(int id) throws AccountDoesNotExistException {
        if (new AccountService().getEntityById(id) == null) {
            throw new AccountDoesNotExistException("Account does not exist");
        } else {
            return new AccountService().getEntityById(id);
        }
    }

    @Override // Admin
    public void issueCard(String name, String cardNumber, String pinNumber, String expirationDate, String cvc, int userTypeId, int cardTypeId) throws CardNumberExistsException {
        if (new CardService().getCardByCardNumber(cardNumber) != null) {
            throw new CardNumberExistsException("Card number already exists");
        }
        User user = new User(name, new UserTypeService().getUserTypeByName(UserTypeEnum.USER.getUserType()));
        if (userTypeId == UserTypeEnum.ADMIN.getCode()) {
            user = new User(name, new UserTypeService().getUserTypeByName(UserTypeEnum.ADMIN.getUserType()));
        }
        UserService userService = new UserService();
        userService.saveEntity(user);
        user = userService.getUserByName(name);
        Account account = new Account(0, new UserService().getEntityById(user.getUserId()));
        if (cardTypeId == CardTypeEnum.CREDIT.getCode()) {
            account = new Account(1000, new UserService().getEntityById(user.getUserId()), 1000);
        }
        AccountService accountService = new AccountService();
        accountService.saveEntity(account);
        Card card = new Card(cardNumber, SHA1.encryptPin(pinNumber), expirationDate, cvc, new CardTypeService().getEntityById(cardTypeId), accountService.getEntityById(account.getAccountId()),
                new CardStatusService().getCardStatusByName(CardStatusEnum.ACTIVE.getCardStatus()));
        new CardService().saveEntity(card);
        LOGGER.debug("New card created for {}", name);
    }


    @Override // Admin
    public int createAccountForExistingUser(String userName, int cardTypeId) {
        Account account = new Account(0, new UserService().getUserByName(userName));
        if (cardTypeId == CardTypeEnum.CREDIT.getCode()) {
            account = new Account(1000, new UserService().getUserByName(userName));
        }
        new AccountService().saveEntity(account);
        LOGGER.debug("New account created for {} ", userName);
        return account.getAccountId();
    }

    @Override // Admin
    public void issueCardToExistingAccount(String cardNumber, String pinNumber, String expirationDate, String cvc, int cardTypeID, int accountID) throws CardNumberExistsException {
        if (new CardService().getCardByCardNumber(cardNumber) != null) {
            throw new CardNumberExistsException("Card number already exists");
        }
        Card card = new Card(cardNumber, SHA1.encryptPin(pinNumber), expirationDate, cvc, new CardTypeService().getEntityById(cardTypeID), new AccountService().getEntityById(accountID), new CardStatusService().getCardStatusByName(CardStatusEnum.ACTIVE.getCardStatus()));
        new CardService().saveEntity(card);
        LOGGER.debug("New card issued to {}", accountID);
    }

    @Override // Admin
    public List<User> seeAllUsers() {
        return new UserService().getAll();
    }

    @Override // Admin
    public void createUser(String name, int userTypeID) {
        User user = new User(name, new UserTypeService().getUserTypeByName(UserTypeEnum.USER.getUserType()));
        if (userTypeID == UserTypeEnum.ADMIN.getCode()) {
            user = new User(name, new UserTypeService().getUserTypeByName(UserTypeEnum.ADMIN.getUserType()));
        }
        System.out.println(user.getUserType().toString());
        new UserService().saveEntity(user);
        LOGGER.debug("New user created for {}", name);
    }

    @Override
    public boolean checkUserNameExist(String userName) {
        return new UserService().getUserByName(userName) != null;
    }
}
