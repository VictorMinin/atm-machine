package com.solvd.atm.persistence.impl;

import com.solvd.atm.models.EventType;
import com.solvd.atm.persistence.IEventTypeDAO;
import com.solvd.atm.utils.MySQLFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class EventTypeDAO implements IEventTypeDAO {

    @Override
    public EventType getEventTypeByName(String eventName) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            IEventTypeDAO mapper = sqlSession.getMapper(IEventTypeDAO.class);
            return mapper.getEventTypeByName(eventName);
        }
    }

    @Override
    public List<EventType> getAll() {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            IEventTypeDAO mapper = sqlSession.getMapper(IEventTypeDAO.class);
            return mapper.getAll();
        }
    }

    @Override
    public EventType getEntityById(int id) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            IEventTypeDAO mapper = sqlSession.getMapper(IEventTypeDAO.class);
            return mapper.getEntityById(id);
        }
    }

    @Override
    public void saveEntity(EventType eventType) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            IEventTypeDAO mapper = sqlSession.getMapper(IEventTypeDAO.class);
            mapper.saveEntity(eventType);
            sqlSession.commit();
        }
    }

    @Override
    public void updateEntity(EventType eventType) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            IEventTypeDAO mapper = sqlSession.getMapper(IEventTypeDAO.class);
            mapper.updateEntity(eventType);
            sqlSession.commit();
        }
    }

    @Override
    public void removeEntityById(int id) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            IEventTypeDAO mapper = sqlSession.getMapper(IEventTypeDAO.class);
            mapper.removeEntityById(id);
            sqlSession.commit();
        }
    }
}
