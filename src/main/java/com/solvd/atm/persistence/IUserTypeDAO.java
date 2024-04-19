package com.solvd.atm.persistence;

import com.solvd.atm.models.UserType;
import org.apache.ibatis.annotations.Param;

public interface IUserTypeDAO extends IBaseDAO<UserType> {

    UserType getUserTypeByName(@Param("typeName") String typeName);
}
