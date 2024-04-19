package com.solvd.atm.service;

import com.solvd.atm.models.CardStatus;
import com.solvd.atm.persistence.ICardStatusDAO;
import com.solvd.atm.persistence.factory.DAOFactory;
import com.solvd.atm.persistence.factory.DAOFactoryEnum;
import com.solvd.atm.persistence.impl.CardStatusDAO;

import java.util.List;

public class CardStatusService implements ICardStatusDAO {

    private final CardStatusDAO cardStatusDAO = (CardStatusDAO) new DAOFactory().getDAO(DAOFactoryEnum.CARD_STATUSES);

    @Override
    public CardStatus getCardStatusByName(String statusName) {
        return cardStatusDAO.getCardStatusByName(statusName);
    }

    @Override
    public List<CardStatus> getAll() {
        return cardStatusDAO.getAll();
    }

    @Override
    public CardStatus getEntityById(int id) {
        return cardStatusDAO.getEntityById(id);
    }

    @Override
    public void saveEntity(CardStatus cardStatus) {
        cardStatusDAO.saveEntity(cardStatus);
    }

    @Override
    public void updateEntity(CardStatus cardStatus) {
        cardStatusDAO.updateEntity(cardStatus);
    }

    @Override
    public void removeEntityById(int id) {
        cardStatusDAO.removeEntityById(id);
    }
}
