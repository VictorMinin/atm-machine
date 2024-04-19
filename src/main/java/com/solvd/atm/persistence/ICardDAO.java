package com.solvd.atm.persistence;

import com.solvd.atm.models.Card;
import com.solvd.atm.models.Transaction;
import com.solvd.atm.utils.customexceptions.BlockedCardException;
import com.solvd.atm.utils.customexceptions.InsufficientFundsException;
import com.solvd.atm.utils.customexceptions.PastMaxCreditException;
import org.apache.ibatis.annotations.Param;

public interface ICardDAO extends IBaseDAO<Card> {

    Card getCardByCardNumber(@Param("cardNumber") String cardNumber);

    Transaction withdrawFromCard(int cardId, double amount) throws BlockedCardException, InsufficientFundsException;

    Transaction depositToCard(int cardId, double amount) throws BlockedCardException, PastMaxCreditException;

    void blockCardById(@Param("cardID") int cardId);

    void unblockCardById(@Param("cardID") int cardId);
}
