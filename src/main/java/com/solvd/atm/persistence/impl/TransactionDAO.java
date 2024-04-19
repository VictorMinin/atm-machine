package com.solvd.atm.persistence.impl;

import com.solvd.atm.enums.CardStatusEnum;
import com.solvd.atm.enums.EventTypeEnum;
import com.solvd.atm.enums.TransactionStatusEnum;
import com.solvd.atm.enums.TransactionTypeEnum;
import com.solvd.atm.models.*;
import com.solvd.atm.persistence.ITransactionDAO;
import com.solvd.atm.utils.MySQLFactory;
import com.solvd.atm.utils.customexceptions.BlockedCardException;
import com.solvd.atm.utils.customexceptions.InsufficientFundsException;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class TransactionDAO implements ITransactionDAO {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public List<Transaction> getAllTransactionByCardId(int cardId) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            ITransactionDAO mapper = sqlSession.getMapper(ITransactionDAO.class);
            return mapper.getAllTransactionByCardId(cardId);
        }
    }

    @Override
    public Transaction moneyTransfer(int senderCardId, int recipientCardId, double amount) throws SQLException, InsufficientFundsException, BlockedCardException {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession(false)) {
            try {
                sqlSession.getConnection().setAutoCommit(false);

                CardDAO cardDao = new CardDAO();
                Card senderCard = cardDao.getEntityById(senderCardId);
                Account senderAccount = senderCard.getAccount();
                Card recipientCard = cardDao.getEntityById(recipientCardId);
                Account recipientAccount = recipientCard.getAccount();

                Transaction transaction = new Transaction();
                transaction.setEvent(new EventDAO().createEvent(EventTypeEnum.TRANSACTION_OPERATION.getEventType()));
                transaction.setTransactionType(new TransactionTypeDAO().getTransactionTypeByName(TransactionTypeEnum.TRANSFER.getTransactionType()));
                transaction.setSenderCard(new CardDAO().getEntityById(senderCardId));
                transaction.setRecipientCard(new CardDAO().getEntityById(recipientCardId));
                transaction.setAmount(amount);

                Boolean insufficientAmount = senderAccount.getAmount() < amount;
                Boolean cardBlocked = senderCard.getCardStatus().getCardStatus().equals(CardStatusEnum.BLOCKED.getCardStatus());
                if (insufficientAmount || cardBlocked) {
                    transaction.setTransactionStatus(new TransactionStatusDAO().getTransactionStatusByName(TransactionStatusEnum.BLOCKED.getStatus()));
                    saveEntity(transaction);
                    if (cardBlocked) {
                        LOGGER.debug("Blocked card {} tried to make a transfer", senderCardId);
                        throw new BlockedCardException("Sender's card is blocked");
                    }
                    throw new InsufficientFundsException("Insufficient funds on sender's account");
                }
                transaction.setTransactionStatus(new TransactionStatusDAO().getTransactionStatusByName(TransactionStatusEnum.COMPLETE.getStatus()));

                senderAccount.setAmount(senderAccount.getAmount() - amount);
                recipientAccount.setAmount(recipientAccount.getAmount() + amount);

                new AccountDAO().updateEntity(recipientAccount);
                new AccountDAO().updateEntity(senderAccount);
                saveEntity(transaction);
                sqlSession.commit();
                LOGGER.debug("Transfer Successful from {} to {}", senderCardId, recipientCardId);
                return transaction;
            } catch (SQLException e) {
                sqlSession.rollback();
                LOGGER.error(e.getMessage());
                return null;
            } finally {
                sqlSession.getConnection().setAutoCommit(true);
            }
        }
    }


    @Override
    public List<Transaction> getAll() {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            ITransactionDAO mapper = sqlSession.getMapper(ITransactionDAO.class);
            return mapper.getAll();
        }
    }

    @Override
    public Transaction getEntityById(int id) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            ITransactionDAO mapper = sqlSession.getMapper(ITransactionDAO.class);
            return mapper.getEntityById(id);
        }
    }

    @Override
    public void saveEntity(Transaction transaction) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            ITransactionDAO mapper = sqlSession.getMapper(ITransactionDAO.class);
            mapper.saveEntity(transaction);
            sqlSession.commit();
        }
    }

    @Override
    public void updateEntity(Transaction transaction) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            ITransactionDAO mapper = sqlSession.getMapper(ITransactionDAO.class);
            mapper.updateEntity(transaction);
            sqlSession.commit();
        }
    }

    @Override
    public void removeEntityById(int id) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            ITransactionDAO mapper = sqlSession.getMapper(ITransactionDAO.class);
            mapper.removeEntityById(id);
            sqlSession.commit();
        }
    }
}
