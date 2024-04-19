package com.solvd.atm.persistence.factory;

import com.solvd.atm.persistence.IBaseDAO;
import com.solvd.atm.persistence.impl.*;

public class DAOFactory implements IBaseDAOFactory {

    public IBaseDAO getDAO(DAOFactoryEnum tableName) {
        return switch (tableName) {
            case ACCOUNTS -> new AccountDAO();
            case CARDS -> new CardDAO();
            case CARD_STATUSES -> new CardStatusDAO();
            case CARD_TYPES -> new CardTypeDAO();
            case EVENTS -> new EventDAO();
            case EVENTS_TYPES -> new EventTypeDAO();
            case TRANSACTIONS -> new TransactionDAO();
            case TRANSACTIONS_STATUSES -> new TransactionStatusDAO();
            case TRANSACTIONS_TYPES -> new TransactionTypeDAO();
            case USERS -> new UserDAO();
            case USER_TYPES -> new UserTypeDAO();
            default -> null;
        };
    }
}
