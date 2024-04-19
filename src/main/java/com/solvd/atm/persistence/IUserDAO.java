package com.solvd.atm.persistence;

import com.solvd.atm.models.User;

public interface IUserDAO extends IBaseDAO<User> {

    User getUserByName(String name);
}
