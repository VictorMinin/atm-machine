package com.solvd.atm.service;

import com.solvd.atm.models.TransactionType;
import com.solvd.atm.persistence.ITransactionTypeDAO;
import com.solvd.atm.persistence.factory.DAOFactory;
import com.solvd.atm.persistence.factory.DAOFactoryEnum;
import com.solvd.atm.persistence.impl.TransactionTypeDAO;

import java.util.List;

public class TransactionTypeService implements ITransactionTypeDAO {

    private final TransactionTypeDAO transactionTypeDAO = (TransactionTypeDAO) new DAOFactory().getDAO(DAOFactoryEnum.TRANSACTIONS_TYPES);

    @Override
    public TransactionType getTransactionTypeByName(String typeName) {
        return transactionTypeDAO.getTransactionTypeByName(typeName);
    }

    @Override
    public List<TransactionType> getAll() {
        return transactionTypeDAO.getAll();
    }

    @Override
    public TransactionType getEntityById(int id) {
        return transactionTypeDAO.getEntityById(id);
    }

    @Override
    public void saveEntity(TransactionType transactionType) {
        transactionTypeDAO.saveEntity(transactionType);
    }

    @Override
    public void updateEntity(TransactionType transactionType) {
        transactionTypeDAO.updateEntity(transactionType);
    }

    @Override
    public void removeEntityById(int id) {
        transactionTypeDAO.removeEntityById(id);
    }
}
