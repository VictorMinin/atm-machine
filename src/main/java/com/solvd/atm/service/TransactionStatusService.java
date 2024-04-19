package com.solvd.atm.service;

import com.solvd.atm.models.TransactionStatus;
import com.solvd.atm.persistence.ITransactionStatusDAO;
import com.solvd.atm.persistence.factory.DAOFactory;
import com.solvd.atm.persistence.factory.DAOFactoryEnum;
import com.solvd.atm.persistence.impl.TransactionStatusDAO;

import java.util.List;

public class TransactionStatusService implements ITransactionStatusDAO {

    private final TransactionStatusDAO transactionStatusDAO = (TransactionStatusDAO) new DAOFactory().getDAO(DAOFactoryEnum.TRANSACTIONS_STATUSES);


    @Override
    public TransactionStatus getTransactionStatusByName(String statusName) {
        return transactionStatusDAO.getTransactionStatusByName(statusName);
    }

    @Override
    public List<TransactionStatus> getAll() {
        return transactionStatusDAO.getAll();
    }

    @Override
    public TransactionStatus getEntityById(int id) {
        return transactionStatusDAO.getEntityById(id);
    }

    @Override
    public void saveEntity(TransactionStatus transactionStatus) {
        transactionStatusDAO.saveEntity(transactionStatus);
    }

    @Override
    public void updateEntity(TransactionStatus transactionStatus) {
        transactionStatusDAO.updateEntity(transactionStatus);
    }

    @Override
    public void removeEntityById(int id) {
        transactionStatusDAO.removeEntityById(id);
    }

}
