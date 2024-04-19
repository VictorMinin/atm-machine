package com.solvd.atm.persistence.impl;

import com.solvd.atm.models.TransactionStatus;
import com.solvd.atm.persistence.ITransactionStatusDAO;
import com.solvd.atm.utils.MySQLFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class TransactionStatusDAO implements ITransactionStatusDAO {


    @Override
    public TransactionStatus getTransactionStatusByName(String statusName) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            ITransactionStatusDAO mapper = sqlSession.getMapper(ITransactionStatusDAO.class);
            return mapper.getTransactionStatusByName(statusName);
        }
    }

    @Override
    public List<TransactionStatus> getAll() {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            ITransactionStatusDAO mapper = sqlSession.getMapper(ITransactionStatusDAO.class);
            return mapper.getAll();
        }
    }

    @Override
    public TransactionStatus getEntityById(int id) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            ITransactionStatusDAO mapper = sqlSession.getMapper(ITransactionStatusDAO.class);
            return mapper.getEntityById(id);
        }
    }

    @Override
    public void saveEntity(TransactionStatus transactionStatus) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            ITransactionStatusDAO mapper = sqlSession.getMapper(ITransactionStatusDAO.class);
            mapper.saveEntity(transactionStatus);
            sqlSession.commit();
        }
    }

    @Override
    public void updateEntity(TransactionStatus transactionStatus) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            ITransactionStatusDAO mapper = sqlSession.getMapper(ITransactionStatusDAO.class);
            mapper.updateEntity(transactionStatus);
            sqlSession.commit();
        }
    }

    @Override
    public void removeEntityById(int id) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            ITransactionStatusDAO mapper = sqlSession.getMapper(ITransactionStatusDAO.class);
            mapper.removeEntityById(id);
            sqlSession.commit();
        }
    }
}
