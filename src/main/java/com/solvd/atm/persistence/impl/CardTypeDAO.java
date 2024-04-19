package com.solvd.atm.persistence.impl;

import com.solvd.atm.models.Card;
import com.solvd.atm.models.CardType;
import com.solvd.atm.persistence.ICardTypeDAO;
import com.solvd.atm.utils.MySQLFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CardTypeDAO implements ICardTypeDAO {

    @Override
    public Card getCardTypeByName(String typeName) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            ICardTypeDAO mapper = sqlSession.getMapper(ICardTypeDAO.class);
            return mapper.getCardTypeByName(typeName);
        }
    }

    @Override
    public List<CardType> getAll() {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            ICardTypeDAO mapper = sqlSession.getMapper(ICardTypeDAO.class);
            return mapper.getAll();
        }
    }

    @Override
    public CardType getEntityById(int id) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            ICardTypeDAO mapper = sqlSession.getMapper(ICardTypeDAO.class);
            return mapper.getEntityById(id);
        }
    }

    @Override
    public void saveEntity(CardType cardType) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            ICardTypeDAO mapper = sqlSession.getMapper(ICardTypeDAO.class);
            mapper.saveEntity(cardType);
            sqlSession.commit();
        }
    }

    @Override
    public void updateEntity(CardType cardType) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            ICardTypeDAO mapper = sqlSession.getMapper(ICardTypeDAO.class);
            mapper.updateEntity(cardType);
            sqlSession.commit();
        }
    }

    @Override
    public void removeEntityById(int id) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            ICardTypeDAO mapper = sqlSession.getMapper(ICardTypeDAO.class);
            mapper.removeEntityById(id);
            sqlSession.commit();
        }
    }
}
