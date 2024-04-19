package com.solvd.atm.service;

import com.solvd.atm.models.Card;
import com.solvd.atm.models.CardType;
import com.solvd.atm.persistence.ICardTypeDAO;
import com.solvd.atm.persistence.factory.DAOFactory;
import com.solvd.atm.persistence.factory.DAOFactoryEnum;
import com.solvd.atm.persistence.impl.CardTypeDAO;

import java.util.List;

public class CardTypeService implements ICardTypeDAO {

    private final CardTypeDAO cardTypeDAO = (CardTypeDAO) new DAOFactory().getDAO(DAOFactoryEnum.CARD_TYPES);

    @Override
    public Card getCardTypeByName(String typeName) {
        return cardTypeDAO.getCardTypeByName(typeName);
    }

    @Override
    public List<CardType> getAll() {
        return cardTypeDAO.getAll();
    }

    @Override
    public CardType getEntityById(int id) {
        return cardTypeDAO.getEntityById(id);
    }

    @Override
    public void saveEntity(CardType cardType) {
        cardTypeDAO.saveEntity(cardType);
    }

    @Override
    public void updateEntity(CardType cardType) {
        cardTypeDAO.updateEntity(cardType);
    }

    @Override
    public void removeEntityById(int id) {
        cardTypeDAO.removeEntityById(id);
    }
}
