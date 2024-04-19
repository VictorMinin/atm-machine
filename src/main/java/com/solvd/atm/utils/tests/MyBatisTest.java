package com.solvd.atm.utils.tests;

import com.solvd.atm.enums.CardStatusEnum;
import com.solvd.atm.enums.EventTypeEnum;
import com.solvd.atm.enums.TransactionStatusEnum;
import com.solvd.atm.enums.TransactionTypeEnum;
import com.solvd.atm.enums.UserTypeEnum;
import com.solvd.atm.models.*;
import com.solvd.atm.persistence.impl.*;
import com.solvd.atm.utils.customexceptions.BlockedCardException;
import com.solvd.atm.utils.customexceptions.InsufficientFundsException;
import com.solvd.atm.utils.customexceptions.PastMaxCreditException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.SQLException;
import java.sql.Timestamp;

public class MyBatisTest {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public static void test() {
        CardTypeDAO cardTypeDAO = new CardTypeDAO();
        CardType cardType = new CardType();
        cardType.setTypeName("testType");
        cardTypeDAO.saveEntity(cardType);
        cardType.setTypeName("changeName");
        cardTypeDAO.updateEntity(cardType);
        cardType = cardTypeDAO.getEntityById(cardType.getCardTypeId());
        if (!cardType.getTypeName().equals("changeName")) {
            LOGGER.error("Failed test in CardTypeDAO");
        }
        cardTypeDAO.removeEntityById(cardType.getCardTypeId());
        cardType = cardTypeDAO.getEntityById(cardType.getCardTypeId());
        if (!(cardType == null)) {
            LOGGER.error("Failed test CardTypeDAO");
        }
        cardType = cardTypeDAO.getAll().get(0);
        CardStatusDAO cardStatusDAO = new CardStatusDAO();
        com.solvd.atm.models.CardStatus cardStatus = new com.solvd.atm.models.CardStatus();
        cardStatus.setCardStatus("testStatus");
        cardStatusDAO.saveEntity(cardStatus);
        cardStatus.setCardStatus("changeName");
        cardStatusDAO.updateEntity(cardStatus);
        cardStatus = cardStatusDAO.getEntityById(cardStatus.getCardStatusId());
        if (!(cardStatus.getCardStatus().equals("changeName"))) {
            LOGGER.error("Failed test in CardStatusDAO");
        }
        cardStatusDAO.removeEntityById(cardStatus.getCardStatusId());
        cardStatus = cardStatusDAO.getEntityById(cardStatus.getCardStatusId());
        if (!(cardStatus == null)) {
            LOGGER.error("Failed test in CardStatusDAO");
        }
        cardStatus = cardStatusDAO.getAll().get(0);
        if (!(cardStatusDAO.getCardStatusByName(CardStatusEnum.BLOCKED.getCardStatus()) != null)) {
            LOGGER.error("Failed test in CardStatusDAO");
        }
        UserTypeDAO userTypeDAO = new UserTypeDAO();
        com.solvd.atm.models.UserType userType = new com.solvd.atm.models.UserType();
        userType.setUserType("testType");
        userTypeDAO.saveEntity(userType);
        userType.setUserType("changeName");
        userTypeDAO.updateEntity(userType);
        userType = userTypeDAO.getEntityById(userType.getUserTypeId());
        if (!(userType.getUserType().equals("changeName"))) {
            LOGGER.error("Failed test in UserTypeDAO");
        }
        userTypeDAO.removeEntityById(userType.getUserTypeId());
        userType = userTypeDAO.getEntityById(userType.getUserTypeId());
        if (!(userType == null)) {
            LOGGER.error("Failed test in UserTypeDAO");
        }
        userType = userTypeDAO.getAll().get(0);
        if (!(userTypeDAO.getUserTypeByName(UserTypeEnum.USER.getUserType()) != null)) {
            LOGGER.error("Failed test in UserTypeDAO");
        }
        EventTypeDAO eventTypeDAO = new EventTypeDAO();
        com.solvd.atm.models.EventType eventType = new com.solvd.atm.models.EventType();
        eventType.setEventName("testName");
        eventTypeDAO.saveEntity(eventType);
        eventType.setEventName("changeName");
        eventTypeDAO.updateEntity(eventType);
        eventType = eventTypeDAO.getEntityById(eventType.getEventTypeId());
        if (!(eventType.getEventName().equals("changeName"))) {
            LOGGER.error("Failed test in EventTypeDAO");
        }
        eventTypeDAO.removeEntityById(eventType.getEventTypeId());
        eventType = eventTypeDAO.getEntityById(eventType.getEventTypeId());
        if (!(eventType == null)) {
            LOGGER.error("Failed test in EventTypeDAO");
        }
        eventType = eventTypeDAO.getAll().get(0);
        EventDAO eventDAO = new EventDAO();
        Event event = new Event();
        event.setDate(new Timestamp(System.currentTimeMillis()));
        event.setEventType(eventType);
        eventDAO.saveEntity(event);
        event.setEventType(eventTypeDAO.getAll().get(1));
        eventDAO.updateEntity(event);
        if (!(event.getEventType().getEventName().equals(eventTypeDAO.getAll().get(1).getEventName()))) {
            LOGGER.error("Failed test in EventDAO.save,update,getbyId");
        }
        eventDAO.removeEntityById(event.getEventId());
        event = eventDAO.getEntityById(event.getEventId());
        if (!((event == null))) {
            LOGGER.error("Failed test in EventDAO.removeEntity");
        }
        event = eventDAO.getAll().get(0);
        if (!(eventTypeDAO.getEventTypeByName(EventTypeEnum.TRANSACTION_OPERATION.getEventType()) != null)) {
            LOGGER.error("Failed test in EventDAO.getAll");
        }
        if (!(eventDAO.createEvent(EventTypeEnum.DEPOSIT_OPERATION.getEventType()) != null)) {
            LOGGER.error("Failed test in EventDAO.createEvent");
        }
        UserDAO userDAO = new UserDAO();
        User user = new User();
        user.setUserType(userType);
        user.setName("testName");
        userDAO.saveEntity(user);
        user.setName("changeName");
        userDAO.updateEntity(user);
        user = userDAO.getEntityById(user.getUserId());
        if (!(user.getName().equals("changeName"))) {
            LOGGER.error("Failed test in UserDAO");
        }
        userDAO.removeEntityById(user.getUserId());
        User tmpUser = userDAO.getEntityById(user.getUserId());
        if (!(tmpUser == null)) {
            LOGGER.error("Failed test in UserDAO");
        }
        userDAO.saveEntity(user);
        AccountDAO accountDAO = new AccountDAO();
        Account account = new Account();
        account.setAmount(900.9);
        account.setUser(user);
        accountDAO.saveEntity(account);
        account.setAmount(600.0);
        accountDAO.updateEntity(account);
        account = accountDAO.getEntityById(account.getAccountId());
        if (!(account.getAmount() == 600.0)) {
            LOGGER.error("Failed test in AccountDAO");
        }
        accountDAO.removeEntityById(account.getAccountId());
        Account tmpAccount = accountDAO.getEntityById(account.getAccountId());
        if (!(tmpAccount == null)) {
            LOGGER.error("Failed test in AccountDAO");
        }
        accountDAO.saveEntity(account);
        double testAmount = 50;
        double previous = account.getAmount();
        accountDAO.deposit(account.getAccountId(), testAmount);
        account = accountDAO.getEntityById(account.getAccountId());
        if (!(account.getAmount() == (previous + testAmount))) {
            LOGGER.error("Failed test in AccountDAO");
        }
        try {
            accountDAO.withdraw(account.getAccountId(), testAmount);
        } catch (InsufficientFundsException e) {
        }
        account = accountDAO.getEntityById(account.getAccountId());
        if (!(account.getAmount() == previous)) {
            LOGGER.error("Failed test in AccountDAO");
        }
        TransactionStatusDAO transactionStatusDAO = new TransactionStatusDAO();
        com.solvd.atm.models.TransactionStatus transactionStatus = new com.solvd.atm.models.TransactionStatus();
        transactionStatus.setStatusName("testName");
        transactionStatusDAO.saveEntity(transactionStatus);
        transactionStatus.setStatusName("changeName");
        transactionStatusDAO.updateEntity(transactionStatus);
        transactionStatus = transactionStatusDAO.getEntityById(transactionStatus.getTransactionStatusId());
        if (!(transactionStatus.getStatusName().equals("changeName"))) {
            LOGGER.error("Failed test in TransactionStatusDAO");
        }
        transactionStatusDAO.removeEntityById(transactionStatus.getTransactionStatusId());
        com.solvd.atm.models.TransactionStatus tmpTransStatus = transactionStatusDAO.getEntityById(transactionStatus.getTransactionStatusId());
        if (!(tmpTransStatus == null)) {
            LOGGER.error("Failed test in TransactionStatusDAO");
        }
        transactionStatusDAO.saveEntity(transactionStatus);
        CardDAO cardDAO = new CardDAO();
        Card card = new Card();
        card.setAccount(account);
        card.setCardNumber("12323423423432");
        card.setCardStatus(cardStatus);
        card.setCardType(cardType);
        card.setCvc("121");
        card.setExpirationDate("11/22");
        card.setPinNumber("1234");
        cardDAO.saveEntity(card);
        card.setCardNumber("nameChange");
        cardDAO.updateEntity(card);
        card = cardDAO.getEntityById(card.getCardId());
        if (!(card.getCardNumber().equals("nameChange"))) {
            LOGGER.error("Failed test in CardDAO");
        }
        cardDAO.removeEntityById(card.getCardId());
        Card tmpCard = cardDAO.getEntityById(card.getCardId());
        if (!(tmpCard == null)) {
            LOGGER.error("Failed test in CardDAO");
        }
        cardDAO.saveEntity(card);
        Card cardCopy = cardDAO.getCardByCardNumber(card.getCardNumber());
        if (!(cardCopy.getCardNumber().equals(card.getCardNumber()))) {
            LOGGER.error("Failed test in CardDAO");
        }
        double previousCardAmount = card.getAccount().getAmount();
        Transaction cardTransaction;
        try {
            cardTransaction = new CardDAO().withdrawFromCard(card.getCardId(), testAmount);
            card = cardDAO.getCardByCardNumber(card.getCardNumber());
            if (!(card.getAccount().getAmount() == (previousCardAmount - testAmount))) {
                LOGGER.error("Failed test in CardDAO");
            }
            if (!(cardTransaction.getAmount() == testAmount)) {
                LOGGER.error("Failed test in CardDAO");
            }
            cardTransaction = cardDAO.depositToCard(card.getCardId(), testAmount);
            card = cardDAO.getCardByCardNumber(card.getCardNumber());
            if (!(card.getAccount().getAmount() == previousCardAmount)) {
                LOGGER.error("Failed test in CardDAO");
            }
            if (!(cardTransaction.getAmount() == testAmount)) {
                LOGGER.error("Failed test in CardDAO");
            }
        } catch (BlockedCardException | InsufficientFundsException e) {
        } catch (PastMaxCreditException e) {
            throw new RuntimeException(e);
        }
        TransactionTypeDAO transactionTypeDAO = new TransactionTypeDAO();
        com.solvd.atm.models.TransactionType transactionType = new com.solvd.atm.models.TransactionType();
        transactionType.setTypeName("testName");
        transactionTypeDAO.saveEntity(transactionType);
        transactionType.setTypeName("changeName");
        transactionTypeDAO.updateEntity(transactionType);
        transactionType = transactionTypeDAO.getEntityById(transactionType.getTransactionTypeId());
        if (!(transactionType.getTypeName().equals("changeName"))) {
            LOGGER.error("Failed test in TransactionTypeDAO");
        }
        transactionTypeDAO.removeEntityById(transactionType.getTransactionTypeId());
        com.solvd.atm.models.TransactionType tmpTransactionType = transactionTypeDAO.getEntityById(transactionType.getTransactionTypeId());
        if (!(tmpTransactionType == null)) {
            LOGGER.error("Failed test in TransactionTypeDAO");
        }
        transactionType = transactionTypeDAO.getAll().get(0);
        if (!(transactionTypeDAO.getTransactionTypeByName(TransactionTypeEnum.TRANSFER.getTransactionType()) != null)) {
            LOGGER.error("Failed test in TransactionTypeDAO");
        }
        TransactionDAO transactionDAO = new TransactionDAO();
        Transaction transaction = new Transaction();
        transaction.setAmount(9900.3);
        transaction.setEvent(event);
        transaction.setTransactionStatus(transactionStatus);
        transaction.setTransactionType(transactionType);
        transaction.setRecipientCard(card);
        transaction.setSenderCard(card);
        transactionDAO.saveEntity(transaction);
        transaction.setAmount(600.0);
        transactionDAO.updateEntity(transaction);
        transaction = transactionDAO.getEntityById(transaction.getTransactionId());
        if (!(transaction.getAmount() == 600.0)) {
            LOGGER.error("Failed test in TransactionDAO.save,update,getbyId");
        }
        transactionDAO.removeEntityById(transaction.getTransactionId());
        transaction = transactionDAO.getEntityById(transaction.getTransactionId());
        if (!(transaction == null) || !(transactionDAO.getAll().get(0) != null) ||
                !(transactionStatusDAO.getTransactionStatusByName(TransactionStatusEnum.BLOCKED.getStatus()) != null) ||
                !(!transactionDAO.getAllTransactionByCardId(1).isEmpty())) {
            LOGGER.error("Failed test in TransactionDAO.getAll,getTransactionStatusByName,getAllTransactionById");
        }
        //Testing Money Transfer
        transaction = transactionDAO.getEntityById(6);
        //System.out.println(transaction.getRecipientCard().getAccount().getAmount());
        double senderAmount = transaction.getSenderCard().getAccount().getAmount();
        double recieverAmount = transaction.getRecipientCard().getAccount().getAmount();
        double transferAmount = 1;
        try {
            Transaction transaction1 = transactionDAO.moneyTransfer(transaction.getSenderCard().getCardId(), transaction.getRecipientCard().getCardId(), transferAmount);
            if (!(transaction1 != null)) {
                LOGGER.error("Failed test in TransactionDAO.moneyTranser(no returned transaction)");
            }
        } catch (SQLException | InsufficientFundsException | BlockedCardException e) {
        }
        transaction = transactionDAO.getEntityById(6);
        if (!((transaction.getSenderCard().getAccount().getAmount() == senderAmount - transferAmount) && (transaction.getRecipientCard().getAccount().getAmount() == recieverAmount + transferAmount))) {
            LOGGER.error("Failed test in TransactionDAO.moneyTransfer(money not transferred)");
        }
        cardDAO.removeEntityById(card.getCardId());
        transactionStatusDAO.removeEntityById(transactionStatus.getTransactionStatusId());
        accountDAO.removeEntityById(account.getAccountId());
        userDAO.removeEntityById(user.getUserId());
    }
}
