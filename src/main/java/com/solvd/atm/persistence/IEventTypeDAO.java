package com.solvd.atm.persistence;

import com.solvd.atm.models.EventType;
import org.apache.ibatis.annotations.Param;

public interface IEventTypeDAO extends IBaseDAO<EventType> {

    EventType getEventTypeByName(@Param("eventName") String eventName);
}
