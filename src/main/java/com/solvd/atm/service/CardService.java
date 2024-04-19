package com.solvd.atm.service;

import com.solvd.atm.models.Card;
import com.solvd.atm.models.Transaction;
import com.solvd.atm.persistence.ICardDAO;
import com.solvd.atm.persistence.factory.DAOFactory;
import com.solvd.atm.persistence.factory.DAOFactoryEnum;
import com.solvd.atm.persistence.impl.CardDAO;
import com.solvd.atm.utils.customexceptions.BlockedCardException;
import com.solvd.atm.utils.customexceptions.InsufficientFundsException;
import com.solvd.atm.utils.customexceptions.PastMaxCreditException;

import java.util.List;

public class CardService implements ICardDAO {

    private final CardDAO cardDAO = (CardDAO) new DAOFactory().getDAO(DAOFactoryEnum.CARDS);

    @Override
    public void blockCardById(int cardId) {
        cardDAO.blockCardById(cardId);
    }

    @Override
    public void unblockCardById(int cardId) {
        cardDAO.unblockCardById(cardId);
    }

    @Override
    public Transaction withdrawFromCard(int cardId, double amount) throws BlockedCardException, InsufficientFundsException {
        return cardDAO.withdrawFromCard(cardId, amount);
    }

    @Override
    public Transaction depositToCard(int cardId, double amount) throws BlockedCardException, PastMaxCreditException {
        return cardDAO.depositToCard(cardId, amount);
    }

    @Override
    public Card getCardByCardNumber(String cardNumber) {
        return cardDAO.getCardByCardNumber(cardNumber);
    }

    @Override
    public List<Card> getAll() {
        return cardDAO.getAll();
    }

    @Override
    public Card getEntityById(int id) {
        return cardDAO.getEntityById(id);
    }

    @Override
    public void saveEntity(Card card) {
        cardDAO.saveEntity(card);
    }

    @Override
    public void updateEntity(Card card) {
        cardDAO.updateEntity(card);
    }

    @Override
    public void removeEntityById(int id) {
        cardDAO.removeEntityById(id);
    }
}
