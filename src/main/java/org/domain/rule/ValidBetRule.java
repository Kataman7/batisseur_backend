package org.domain.rule;

import org.domain.model.Board;

public class ValidBetRule implements GameRule
{
    private final int betValue;

    public ValidBetRule(int betValue) {
        this.betValue = betValue;
    }


    @Override
    public boolean isApplicable(Board board) {
        return betValue > 0 && betValue > board.getBetValue();
    }

    @Override
    public int getCode() {
        return 0;
    }
}
