package com.solvd.atm.persistence;

import com.solvd.atm.models.TransactionType;
import org.apache.ibatis.annotations.Param;

public interface ITransactionTypeDAO extends IBaseDAO<TransactionType> {

    TransactionType getTransactionTypeByName(@Param("typeName") String typeName);
}
