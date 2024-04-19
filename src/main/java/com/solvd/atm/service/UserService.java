package com.solvd.atm.service;

import com.solvd.atm.models.User;
import com.solvd.atm.persistence.IUserDAO;
import com.solvd.atm.persistence.factory.DAOFactory;
import com.solvd.atm.persistence.factory.DAOFactoryEnum;
import com.solvd.atm.persistence.impl.UserDAO;

import java.util.List;

public class UserService implements IUserDAO {

    private final UserDAO userDAO = (UserDAO) new DAOFactory().getDAO(DAOFactoryEnum.USERS);

    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @Override
    public User getEntityById(int id) {
        return userDAO.getEntityById(id);
    }

    @Override
    public void saveEntity(User user) {
        userDAO.saveEntity(user);
    }

    @Override
    public void updateEntity(User user) {
        userDAO.updateEntity(user);
    }

    @Override
    public void removeEntityById(int id) {
        userDAO.removeEntityById(id);
    }

    @Override
    public User getUserByName(String name) {
        return userDAO.getUserByName(name);
    }
}
