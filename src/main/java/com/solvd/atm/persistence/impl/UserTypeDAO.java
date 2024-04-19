package com.solvd.atm.persistence.impl;

import com.solvd.atm.models.UserType;
import com.solvd.atm.persistence.IUserTypeDAO;
import com.solvd.atm.utils.MySQLFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserTypeDAO implements IUserTypeDAO {


    @Override
    public UserType getUserTypeByName(String typeName) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            IUserTypeDAO mapper = sqlSession.getMapper(IUserTypeDAO.class);
            return mapper.getUserTypeByName(typeName);
        }
    }

    @Override
    public List<UserType> getAll() {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            IUserTypeDAO mapper = sqlSession.getMapper(IUserTypeDAO.class);
            return mapper.getAll();
        }
    }

    @Override
    public UserType getEntityById(int id) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            IUserTypeDAO mapper = sqlSession.getMapper(IUserTypeDAO.class);
            return mapper.getEntityById(id);
        }
    }

    @Override
    public void saveEntity(UserType userType) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            IUserTypeDAO mapper = sqlSession.getMapper(IUserTypeDAO.class);
            mapper.saveEntity(userType);
            sqlSession.commit();
        }
    }

    @Override
    public void updateEntity(UserType userType) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            IUserTypeDAO mapper = sqlSession.getMapper(IUserTypeDAO.class);
            mapper.updateEntity(userType);
            sqlSession.commit();
        }
    }

    @Override
    public void removeEntityById(int id) {
        try (SqlSession sqlSession = MySQLFactory.getSqlSessionFactory().openSession()) {
            IUserTypeDAO mapper = sqlSession.getMapper(IUserTypeDAO.class);
            mapper.removeEntityById(id);
            sqlSession.commit();
        }
    }
}
