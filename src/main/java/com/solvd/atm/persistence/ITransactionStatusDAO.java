package com.solvd.atm.persistence;

import com.solvd.atm.models.TransactionStatus;
import org.apache.ibatis.annotations.Param;

public interface ITransactionStatusDAO extends IBaseDAO<TransactionStatus> {

    TransactionStatus getTransactionStatusByName(@Param("statusName") String statusName);
}
