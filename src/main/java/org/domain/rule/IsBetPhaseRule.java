package org.domain.rule;

import org.domain.model.Board;

public class IsBetPhaseRule implements GameRule
{

    @Override
    public boolean isApplicable(Board board) {
        return board.getBetValue() > 0;
    }

    @Override
    public int getCode() {
        return 0;
    }
}
