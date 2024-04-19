package com.solvd.atm.service;

import com.solvd.atm.models.EventType;
import com.solvd.atm.persistence.IEventTypeDAO;
import com.solvd.atm.persistence.factory.DAOFactory;
import com.solvd.atm.persistence.factory.DAOFactoryEnum;
import com.solvd.atm.persistence.impl.EventTypeDAO;

import java.util.List;

public class EventTypeService implements IEventTypeDAO {

    private final EventTypeDAO eventTypeDAO = (EventTypeDAO) new DAOFactory().getDAO(DAOFactoryEnum.EVENTS_TYPES);

    @Override
    public EventType getEventTypeByName(String eventName) {
        return eventTypeDAO.getEventTypeByName(eventName);
    }

    @Override
    public List<EventType> getAll() {
        return eventTypeDAO.getAll();
    }

    @Override
    public EventType getEntityById(int id) {
        return eventTypeDAO.getEntityById(id);
    }

    @Override
    public void saveEntity(EventType eventType) {
        eventTypeDAO.saveEntity(eventType);
    }

    @Override
    public void updateEntity(EventType eventType) {
        eventTypeDAO.updateEntity(eventType);
    }

    @Override
    public void removeEntityById(int id) {
        eventTypeDAO.removeEntityById(id);
    }
}
