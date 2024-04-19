package com.solvd.atm.service;

import com.solvd.atm.models.Event;
import com.solvd.atm.persistence.IEventDAO;
import com.solvd.atm.persistence.factory.DAOFactory;
import com.solvd.atm.persistence.factory.DAOFactoryEnum;
import com.solvd.atm.persistence.impl.EventDAO;

import java.util.List;

public class EventService implements IEventDAO {

    private final EventDAO eventDAO = (EventDAO) new DAOFactory().getDAO(DAOFactoryEnum.EVENTS);

    @Override
    public Event createEvent(String eventType) {
        return eventDAO.createEvent(eventType);
    }

    @Override
    public List<Event> getAll() {
        return eventDAO.getAll();
    }

    @Override
    public Event getEntityById(int id) {
        return eventDAO.getEntityById(id);
    }

    @Override
    public void saveEntity(Event event) {
        eventDAO.saveEntity(event);
    }

    @Override
    public void updateEntity(Event event) {
        eventDAO.updateEntity(event);
    }

    @Override
    public void removeEntityById(int id) {
        eventDAO.removeEntityById(id);
    }
}
