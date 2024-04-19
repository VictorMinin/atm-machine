package com.solvd.atm.service;

import com.solvd.atm.models.UserType;
import com.solvd.atm.persistence.IUserTypeDAO;
import com.solvd.atm.persistence.factory.DAOFactory;
import com.solvd.atm.persistence.factory.DAOFactoryEnum;
import com.solvd.atm.persistence.impl.UserTypeDAO;

import java.util.List;

public class UserTypeService implements IUserTypeDAO {

    private final UserTypeDAO userTypeDAO = (UserTypeDAO) new DAOFactory().getDAO(DAOFactoryEnum.USER_TYPES);

    @Override
    public UserType getUserTypeByName(String typeName) {
        return userTypeDAO.getUserTypeByName(typeName);
    }

    @Override
    public List<UserType> getAll() {
        return userTypeDAO.getAll();
    }

    @Override
    public UserType getEntityById(int id) {
        return userTypeDAO.getEntityById(id);
    }

    @Override
    public void saveEntity(UserType userType) {
        userTypeDAO.saveEntity(userType);
    }

    @Override
    public void updateEntity(UserType userType) {
        userTypeDAO.updateEntity(userType);
    }

    @Override
    public void removeEntityById(int id) {
        userTypeDAO.removeEntityById(id);
    }
}
