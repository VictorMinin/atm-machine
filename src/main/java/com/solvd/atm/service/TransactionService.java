package com.solvd.atm.service;

import com.solvd.atm.models.Transaction;
import com.solvd.atm.persistence.ITransactionDAO;
import com.solvd.atm.persistence.factory.DAOFactory;
import com.solvd.atm.persistence.factory.DAOFactoryEnum;
import com.solvd.atm.persistence.impl.TransactionDAO;
import com.solvd.atm.utils.customexceptions.BlockedCardException;
import com.solvd.atm.utils.customexceptions.InsufficientFundsException;

import java.sql.SQLException;
import java.util.List;

public class TransactionService implements ITransactionDAO {

    private final TransactionDAO transactionDAO = (TransactionDAO) new DAOFactory().getDAO(DAOFactoryEnum.TRANSACTIONS);

    @Override
    public Transaction moneyTransfer(int senderCardId, int recipientCardId, double amount) throws SQLException, InsufficientFundsException, BlockedCardException {
        return transactionDAO.moneyTransfer(senderCardId, recipientCardId, amount);
    }

    @Override
    public List<Transaction> getAllTransactionByCardId(int cardId) {
        return transactionDAO.getAllTransactionByCardId(cardId);
    }

    @Override
    public List<Transaction> getAll() {
        return transactionDAO.getAll();
    }

    @Override
    public Transaction getEntityById(int id) {
        return transactionDAO.getEntityById(id);
    }

    @Override
    public void saveEntity(Transaction transaction) {
        transactionDAO.saveEntity(transaction);
    }

    @Override
    public void updateEntity(Transaction transaction) {
        transactionDAO.updateEntity(transaction);
    }

    @Override
    public void removeEntityById(int id) {
        transactionDAO.removeEntityById(id);
    }

}
