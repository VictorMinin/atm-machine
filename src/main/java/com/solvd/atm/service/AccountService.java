package com.solvd.atm.service;

import com.solvd.atm.models.Account;
import com.solvd.atm.persistence.IAccountDAO;
import com.solvd.atm.persistence.factory.DAOFactory;
import com.solvd.atm.persistence.factory.DAOFactoryEnum;
import com.solvd.atm.persistence.impl.AccountDAO;
import com.solvd.atm.utils.customexceptions.InsufficientFundsException;

import java.util.List;

public class AccountService implements IAccountDAO {

    private final AccountDAO accountDAO = (AccountDAO) new DAOFactory().getDAO(DAOFactoryEnum.ACCOUNTS);

    @Override
    public void withdraw(int accountId, double amount) throws InsufficientFundsException {
        accountDAO.withdraw(accountId, amount);
    }

    @Override
    public void deposit(int accountId, double amount) {
        accountDAO.deposit(accountId, amount);
    }

    @Override
    public List<Account> getAll() {
        return accountDAO.getAll();
    }

    @Override
    public Account getEntityById(int id) {
        return accountDAO.getEntityById(id);
    }

    @Override
    public void saveEntity(Account account) {
        accountDAO.saveEntity(account);
    }

    @Override
    public void updateEntity(Account account) {
        accountDAO.updateEntity(account);
    }

    @Override
    public Account getAccountByUserID(int userID) {
        return accountDAO.getAccountByUserID(userID);
    }

    @Override
    public void removeEntityById(int id) {
        accountDAO.removeEntityById(id);
    }


}
