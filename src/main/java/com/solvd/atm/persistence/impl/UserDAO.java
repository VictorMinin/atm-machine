package com.solvd.atm.persistence.impl;

import com.solvd.atm.models.User;
import com.solvd.atm.persistence.IUserDAO;
import com.solvd.atm.utils.MySQLFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserDAO implements IUserDAO {

    @Override
    public List<User> getAll() {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            IUserDAO mapper = sqlSession.getMapper(IUserDAO.class);
            return mapper.getAll();
        }
    }

    @Override
    public User getEntityById(int id) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            IUserDAO mapper = sqlSession.getMapper(IUserDAO.class);
            return mapper.getEntityById(id);
        }
    }

    @Override
    public void saveEntity(User user) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            IUserDAO mapper = sqlSession.getMapper(IUserDAO.class);
            mapper.saveEntity(user);
            sqlSession.commit();
        }
    }

    @Override
    public void updateEntity(User user) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            IUserDAO mapper = sqlSession.getMapper(IUserDAO.class);
            mapper.updateEntity(user);
            sqlSession.commit();
        }
    }

    @Override
    public void removeEntityById(int id) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            IUserDAO mapper = sqlSession.getMapper(IUserDAO.class);
            mapper.removeEntityById(id);
            sqlSession.commit();
        }
    }

    @Override
    public User getUserByName(String name) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            IUserDAO mapper = sqlSession.getMapper(IUserDAO.class);
            return mapper.getUserByName(name);
        }
    }
}
