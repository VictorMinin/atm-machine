package com.solvd.atm.persistence;

import com.solvd.atm.models.Account;
import com.solvd.atm.utils.customexceptions.InsufficientFundsException;
import org.apache.ibatis.annotations.Param;

public interface IAccountDAO extends IBaseDAO<Account> {

    void withdraw(@Param("accountId") int accountId, @Param("amount") double amount) throws InsufficientFundsException;

    void deposit(@Param("accountId") int accountId, @Param("amount") double amount);

    Account getAccountByUserID(int userID);
}
