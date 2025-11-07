package org.domain.rule;

import org.domain.model.Board;

public class ValidBettorRule extends AbstractPlayerRule
{
    public ValidBettorRule(String playerName)
    {
        super(playerName);
    }

    @Override
    public boolean isApplicable(Board board)
    {
        return board.getBettor().getName().equals(getPlayerName());
    }

    @Override
    public int getCode() {
        return 0;
    }
}
