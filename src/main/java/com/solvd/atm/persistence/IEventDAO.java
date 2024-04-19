package com.solvd.atm.persistence;

import com.solvd.atm.models.Event;
import org.apache.ibatis.annotations.Param;

public interface IEventDAO extends IBaseDAO<com.solvd.atm.models.Event> {

    Event createEvent(@Param("eventType") String eventType);
}
