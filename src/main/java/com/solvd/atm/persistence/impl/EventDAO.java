package com.solvd.atm.persistence.impl;

import com.solvd.atm.models.Event;
import com.solvd.atm.persistence.IEventDAO;
import com.solvd.atm.utils.MySQLFactory;
import org.apache.ibatis.session.SqlSession;

import java.sql.Timestamp;
import java.util.List;

public class EventDAO implements IEventDAO {

    @Override
    public Event createEvent(String eventType) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            IEventDAO mapper = sqlSession.getMapper(IEventDAO.class);
            Event event = new Event(new EventTypeDAO().getEventTypeByName(eventType), new Timestamp(System.currentTimeMillis()));
            saveEntity(event);
            return event;
        }
    }

    @Override
    public List<com.solvd.atm.models.Event> getAll() {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            IEventDAO mapper = sqlSession.getMapper(IEventDAO.class);
            return mapper.getAll();
        }
    }

    @Override
    public com.solvd.atm.models.Event getEntityById(int id) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            IEventDAO mapper = sqlSession.getMapper(IEventDAO.class);
            return mapper.getEntityById(id);
        }
    }

    @Override
    public void saveEntity(com.solvd.atm.models.Event event) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            IEventDAO mapper = sqlSession.getMapper(IEventDAO.class);
            mapper.saveEntity(event);
            sqlSession.commit();
        }
    }

    @Override
    public void updateEntity(com.solvd.atm.models.Event event) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            IEventDAO mapper = sqlSession.getMapper(IEventDAO.class);
            mapper.updateEntity(event);
            sqlSession.commit();
        }
    }

    @Override
    public void removeEntityById(int id) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            IEventDAO mapper = sqlSession.getMapper(IEventDAO.class);
            mapper.removeEntityById(id);
            sqlSession.commit();
        }
    }
}
