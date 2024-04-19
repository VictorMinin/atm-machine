package com.solvd.atm.persistence.impl;

import com.solvd.atm.enums.CardStatusEnum;
import com.solvd.atm.enums.EventTypeEnum;
import com.solvd.atm.enums.TransactionStatusEnum;
import com.solvd.atm.enums.TransactionTypeEnum;
import com.solvd.atm.models.*;
import com.solvd.atm.persistence.ICardDAO;
import com.solvd.atm.utils.MySQLFactory;
import com.solvd.atm.utils.customexceptions.BlockedCardException;
import com.solvd.atm.utils.customexceptions.InsufficientFundsException;
import com.solvd.atm.utils.customexceptions.PastMaxCreditException;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CardDAO implements ICardDAO {

    @Override
    public Transaction withdrawFromCard(int cardId, double amount) throws BlockedCardException, InsufficientFundsException {
        Card card = getEntityById(cardId);
        AccountDAO accountDAO = new AccountDAO();
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setSenderCard(card);
        transaction.setTransactionType(new TransactionTypeDAO().getTransactionTypeByName(TransactionTypeEnum.WITHDRAW.getTransactionType()));
        transaction.setEvent(new EventDAO().createEvent(EventTypeEnum.WITHDRAW_OPERATION.getEventType()));
        if (card.getCardStatus().getCardStatus().equals(CardStatusEnum.BLOCKED.getCardStatus())) {
            transaction.setTransactionStatus(new TransactionStatusDAO().getTransactionStatusByName(TransactionStatusEnum.BLOCKED.getStatus()));
            new TransactionDAO().saveEntity(transaction);
            throw new BlockedCardException("Card with id: " + cardId + " is Blocked");
        }
        try {
            accountDAO.withdraw(card.getAccount().getAccountId(), amount);
        } catch (InsufficientFundsException e) {
            transaction.setTransactionStatus(new TransactionStatusDAO().getTransactionStatusByName(TransactionStatusEnum.BLOCKED.getStatus()));
            new TransactionDAO().saveEntity(transaction);
            throw new InsufficientFundsException(e.getMessage());
        }
        card.setAccount(accountDAO.getEntityById(card.getAccount().getAccountId()));
        transaction.setTransactionStatus(new TransactionStatusDAO().getTransactionStatusByName(TransactionStatusEnum.COMPLETE.getStatus()));
        new TransactionDAO().saveEntity(transaction);
        return transaction;
    }

    @Override
    public Transaction depositToCard(int cardId, double amount) throws BlockedCardException, PastMaxCreditException {
        AccountDAO accountDAO = new AccountDAO();
        Card card = getEntityById(cardId);
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setSenderCard(card);
        transaction.setTransactionType(new TransactionTypeDAO().getTransactionTypeByName(TransactionTypeEnum.DEPOSIT.getTransactionType()));
        transaction.setEvent(new EventDAO().createEvent(EventTypeEnum.DEPOSIT_OPERATION.getEventType()));
        boolean cardBlocked = card.getCardStatus().getCardStatus().equals(CardStatusEnum.BLOCKED.getCardStatus());
        boolean pastMaxCredit = ((card.getAccount().getCreditMax() != 0.0) && ((card.getAccount().getAmount() + amount) > card.getAccount().getCreditMax()));
        if (cardBlocked || pastMaxCredit) {
            transaction.setTransactionStatus(new TransactionStatusDAO().getTransactionStatusByName(TransactionStatusEnum.BLOCKED.getStatus()));
            new TransactionDAO().saveEntity(transaction);
            if (cardBlocked) {
                throw new BlockedCardException("Card: " + card.getCardNumber() + " is blocked");
            }
            throw new PastMaxCreditException("Credit Card: " + card.getCardNumber() + " cannot deposit past what is owed");
        }
        accountDAO.deposit(card.getAccount().getAccountId(), amount);
        card.setAccount(accountDAO.getEntityById(card.getAccount().getAccountId()));
        transaction.setTransactionStatus(new TransactionStatusDAO().getTransactionStatusByName(TransactionStatusEnum.COMPLETE.getStatus()));
        new TransactionDAO().saveEntity(transaction);
        return transaction;
    }

    @Override
    public void blockCardById(int cardId) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            ICardDAO mapper = sqlSession.getMapper(ICardDAO.class);
            mapper.blockCardById(cardId);
            sqlSession.commit();
        }
    }

    @Override
    public void unblockCardById(int cardId) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            ICardDAO mapper = sqlSession.getMapper(ICardDAO.class);
            mapper.unblockCardById(cardId);
            sqlSession.commit();
        }
    }

    @Override
    public Card getCardByCardNumber(String cardNumber) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            ICardDAO mapper = sqlSession.getMapper(ICardDAO.class);
            return mapper.getCardByCardNumber(cardNumber);
        }
    }

    @Override
    public List<Card> getAll() {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            ICardDAO mapper = sqlSession.getMapper(ICardDAO.class);
            return mapper.getAll();
        }
    }

    @Override
    public Card getEntityById(int id) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            ICardDAO mapper = sqlSession.getMapper(ICardDAO.class);
            return mapper.getEntityById(id);
        }
    }

    @Override
    public void saveEntity(Card card) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            ICardDAO mapper = sqlSession.getMapper(ICardDAO.class);
            mapper.saveEntity(card);
            sqlSession.commit();
        }
    }

    @Override
    public void updateEntity(Card card) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            ICardDAO mapper = sqlSession.getMapper(ICardDAO.class);
            mapper.updateEntity(card);
            sqlSession.commit();
        }
    }

    @Override
    public void removeEntityById(int id) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            ICardDAO mapper = sqlSession.getMapper(ICardDAO.class);
            mapper.removeEntityById(id);
            sqlSession.commit();
        }
    }
}
