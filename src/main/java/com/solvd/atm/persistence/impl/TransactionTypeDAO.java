package com.solvd.atm.persistence.impl;

import com.solvd.atm.models.TransactionType;
import com.solvd.atm.persistence.ITransactionTypeDAO;
import com.solvd.atm.utils.MySQLFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class TransactionTypeDAO implements ITransactionTypeDAO {

    @Override
    public TransactionType getTransactionTypeByName(String typeName) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            ITransactionTypeDAO mapper = sqlSession.getMapper(ITransactionTypeDAO.class);
            return mapper.getTransactionTypeByName(typeName);
        }
    }

    @Override
    public List<TransactionType> getAll() {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            ITransactionTypeDAO mapper = sqlSession.getMapper(ITransactionTypeDAO.class);
            return mapper.getAll();
        }
    }

    @Override
    public TransactionType getEntityById(int id) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            ITransactionTypeDAO mapper = sqlSession.getMapper(ITransactionTypeDAO.class);
            return mapper.getEntityById(id);
        }
    }

    @Override
    public void saveEntity(TransactionType transactionType) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            ITransactionTypeDAO mapper = sqlSession.getMapper(ITransactionTypeDAO.class);
            mapper.saveEntity(transactionType);
            sqlSession.commit();
        }
    }

    @Override
    public void updateEntity(TransactionType transactionType) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            ITransactionTypeDAO mapper = sqlSession.getMapper(ITransactionTypeDAO.class);
            mapper.updateEntity(transactionType);
            sqlSession.commit();
        }
    }

    @Override
    public void removeEntityById(int id) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            ITransactionTypeDAO mapper = sqlSession.getMapper(ITransactionTypeDAO.class);
            mapper.removeEntityById(id);
            sqlSession.commit();
        }
    }
}
