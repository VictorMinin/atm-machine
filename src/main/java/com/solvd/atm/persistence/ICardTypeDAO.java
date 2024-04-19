package com.solvd.atm.persistence;

import com.solvd.atm.models.Card;
import com.solvd.atm.models.CardType;
import org.apache.ibatis.annotations.Param;

public interface ICardTypeDAO extends IBaseDAO<CardType> {
    Card getCardTypeByName(@Param("typeName") String typeName);
}
