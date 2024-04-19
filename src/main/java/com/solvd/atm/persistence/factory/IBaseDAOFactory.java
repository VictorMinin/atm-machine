package com.solvd.atm.persistence.factory;

import com.solvd.atm.persistence.IBaseDAO;

public interface IBaseDAOFactory {

    IBaseDAO getDAO(DAOFactoryEnum tableName);
}
