package com.solvd.atm.interfaces;

import com.solvd.atm.enums.AtmState;

public interface ICheckUserAtmActionInput {
    AtmState checkUserAtmAction(String userAtmAction);
}
