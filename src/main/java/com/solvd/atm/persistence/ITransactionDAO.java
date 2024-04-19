package com.solvd.atm.persistence;

import com.solvd.atm.models.Transaction;
import com.solvd.atm.utils.customexceptions.BlockedCardException;
import com.solvd.atm.utils.customexceptions.InsufficientFundsException;

import java.sql.SQLException;
import java.util.List;

public interface ITransactionDAO extends IBaseDAO<Transaction> {

    Transaction moneyTransfer(int senderCardId,
                              int recipientCardId,
                              double amount) throws SQLException, InsufficientFundsException, BlockedCardException;

    List<Transaction> getAllTransactionByCardId(int cardId);
}
