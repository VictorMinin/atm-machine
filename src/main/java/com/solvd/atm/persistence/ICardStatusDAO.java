package com.solvd.atm.persistence;

import com.solvd.atm.models.CardStatus;
import org.apache.ibatis.annotations.Param;

public interface ICardStatusDAO extends IBaseDAO<CardStatus> {

    CardStatus getCardStatusByName(@Param("statusName") String statusName);
}
