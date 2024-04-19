package com.solvd.atm.persistence.impl;

import com.solvd.atm.models.Account;
import com.solvd.atm.persistence.IAccountDAO;
import com.solvd.atm.utils.MySQLFactory;
import com.solvd.atm.utils.customexceptions.InsufficientFundsException;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class AccountDAO implements IAccountDAO {
    @Override
    public void withdraw(int accountId, double amount) throws InsufficientFundsException {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            IAccountDAO mapper = sqlSession.getMapper(IAccountDAO.class);
            Account account = getEntityById(accountId);
            if (account.getAmount() < amount) {
                throw new InsufficientFundsException("Not enough funds in senders Account");
            }
            account.setAmount(account.getAmount() - amount);
            mapper.withdraw(accountId, amount);
            sqlSession.commit();
        }
    }

    @Override
    public void deposit(int accountId, double amount) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            IAccountDAO mapper = sqlSession.getMapper(IAccountDAO.class);
            mapper.deposit(accountId, amount);
            sqlSession.commit();
            Account account = getEntityById(accountId);
            account.setAmount(account.getAmount() + amount);
        }
    }

    @Override
    public List<Account> getAll() {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            IAccountDAO mapper = sqlSession.getMapper(IAccountDAO.class);
            return mapper.getAll();
        }
    }

    @Override
    public Account getEntityById(int id) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            IAccountDAO mapper = sqlSession.getMapper(IAccountDAO.class);
            return mapper.getEntityById(id);
        }
    }

    @Override
    public void saveEntity(Account account) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            IAccountDAO mapper = sqlSession.getMapper(IAccountDAO.class);
            mapper.saveEntity(account);
            sqlSession.commit();
        }
    }

    @Override
    public void updateEntity(Account account) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            IAccountDAO mapper = sqlSession.getMapper(IAccountDAO.class);
            mapper.updateEntity(account);
            sqlSession.commit();
        }
    }

    @Override
    public void removeEntityById(int id) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            IAccountDAO mapper = sqlSession.getMapper(IAccountDAO.class);
            mapper.removeEntityById(id);
            sqlSession.commit();
        }
    }

    @Override
    public Account getAccountByUserID(int userID) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            IAccountDAO mapper = sqlSession.getMapper(IAccountDAO.class);
            return mapper.getAccountByUserID(userID);
        }
    }
}
