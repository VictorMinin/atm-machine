package com.solvd.atm.persistence.impl;

import com.solvd.atm.models.CardStatus;
import com.solvd.atm.persistence.ICardStatusDAO;
import com.solvd.atm.utils.MySQLFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CardStatusDAO implements ICardStatusDAO {


    @Override
    public CardStatus getCardStatusByName(String statusName) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            ICardStatusDAO mapper = sqlSession.getMapper(ICardStatusDAO.class);
            return mapper.getCardStatusByName(statusName);
        }
    }

    @Override
    public List<CardStatus> getAll() {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            ICardStatusDAO mapper = sqlSession.getMapper(ICardStatusDAO.class);
            return mapper.getAll();
        }
    }

    @Override
    public CardStatus getEntityById(int id) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            ICardStatusDAO mapper = sqlSession.getMapper(ICardStatusDAO.class);
            return mapper.getEntityById(id);
        }
    }

    @Override
    public void saveEntity(CardStatus cardStatus) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            ICardStatusDAO mapper = sqlSession.getMapper(ICardStatusDAO.class);
            mapper.saveEntity(cardStatus);
            sqlSession.commit();
        }
    }

    @Override
    public void updateEntity(CardStatus cardStatus) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            ICardStatusDAO mapper = sqlSession.getMapper(ICardStatusDAO.class);
            mapper.updateEntity(cardStatus);
            sqlSession.commit();
        }
    }

    @Override
    public void removeEntityById(int id) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            ICardStatusDAO mapper = sqlSession.getMapper(ICardStatusDAO.class);
            mapper.removeEntityById(id);
            sqlSession.commit();
        }
    }
}
